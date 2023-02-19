package rest.tests

import io.qameta.allure.Feature
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import rest.generators.StepGenerator
import rest.pojos.BuildType
import rest.pojos.EntityShort
import rest.services.*
import rest.steps.GeneralSteps

@Feature("Build Step")
@DisplayName("Build Step Tests API")
class BuildStepTests {
    private lateinit var build: BuildType

    @BeforeEach
    fun createBuild(){
        build = GeneralSteps.createProjectWithBuildConfig()
    }

    @AfterEach
    fun deleteProject(){
        GeneralSteps.deleteProject(build.projectId)
    }

    @Test
    @DisplayName("Add command line step to build config")
    fun addStepTest(){
        val step = BuildStepService.create(StepGenerator.commandStep("echo hello"), build.id).decode()
        assertThat(BuildStepService.getById(step.id, build.id).decode())
            .withFailMessage("Build step with test id should be equal to created one")
            .isEqualTo(step)

        val buildSteps = BuildStepService.getAll(build.id)
        assertThat(buildSteps).hasSize(1)
        assertThat(buildSteps[0]).isEqualTo(step.short())
    }

    @Test
    @DisplayName("Add  two steps tp build config")
    fun addTwoStepTest(){
        val commandStep = BuildStepService.create(StepGenerator.commandStep("echo hello"), build.id).decode()
        val kotlinStep = BuildStepService.create(StepGenerator.kotlinStep("echo hello"), build.id).decode()

        val buildSteps = BuildStepService.getAll(build.id)
        assertThat(buildSteps)
            .withFailMessage("In the build config should be exactly 2 steps")
            .hasSize(2)
        assertThat(buildSteps)
            .withFailMessage("List of steps should contain both created steps")
            .contains(
                EntityShort(commandStep.id, commandStep.name),
                EntityShort(kotlinStep.id, kotlinStep.name)
            )
    }

    @Test
    @DisplayName("Delete steps from build config")
    fun deleteStepTest(){
        val commandStep = BuildStepService.create(StepGenerator.commandStep("echo hello"), build.id).decode()
        BuildStepService.deleteById(commandStep.id, build.id)
        BuildStepService.getById(commandStep.id, build.id).assert().statusCode(404)
        assertThat(BuildStepService.getAll(build.id))
            .withFailMessage("List of steps should not contain the deleted one")
            .doesNotContain(commandStep.short())
    }
}