package rest.tests

import io.qameta.allure.Feature
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import rest.generators.StepGenerator
import rest.pojos.Build
import rest.pojos.BuildType
import rest.services.*
import rest.steps.GeneralSteps

@Feature("Build")
@DisplayName("Build Test API")
class BuildTests {
    private lateinit var buildConfig: BuildType
    lateinit var build: Build

    @BeforeEach
    fun createBuild(){
        buildConfig = GeneralSteps.createProjectWithBuildConfig()
        BuildStepService.create(StepGenerator.commandStep("echo hello"), buildConfig.id)
        build = BuildService.addToQueue(buildConfig.id).decode()
    }

    @AfterEach
    fun deleteProject(){
        GeneralSteps.deleteProject(buildConfig.projectId)
    }

    @Test
    @DisplayName("Add build to queue")
    fun addBuildToQueueTest(){
        assertThat(BuildService.getById(build.id).decode())
            .withFailMessage("Build should be equal to the created one")
            .isEqualTo(build)
    }

    @Test
    @DisplayName("Delete build")
    fun deleteBuild(){
        BuildService.deleteById(build.id)
        BuildService.getById(build.id).assert().statusCode(404)
        assertThat(BuildService.getAll())
            .withFailMessage("List of builds should not contain the deleted one")
            .doesNotContain(build)
    }
}