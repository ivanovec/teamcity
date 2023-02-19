package ui.tests

import io.qameta.allure.Feature
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import rest.generators.StepGenerator
import rest.pojos.BuildType
import rest.pojos.User
import rest.services.BuildStepService
import rest.steps.GeneralSteps
import ui.pages.LoginPage
import java.time.LocalDate

@DisplayName("Build Tests UI")
@Feature("Build")
class BuildTests: BaseTest() {
    private lateinit var build: BuildType
    private lateinit var user: User


    @BeforeEach
    fun createBuild(){
        build = GeneralSteps.createProjectWithBuildConfig()
        user = GeneralSteps.createUser()
    }

    @AfterEach
    fun deleteBuild(){
        GeneralSteps.deleteProject(build.projectId)
        GeneralSteps.deleteUser(user.id)
    }
    @Test()
    @DisplayName("Run build from build configuration page")
    fun runBuildTest(){
        val script = "echo \"I'm Guybrush Threepwood, mighty pirate!\""
        val numberOfBuilds = 1
        BuildStepService.create(StepGenerator.commandStep(script), build.id)

        val buildPage =
            LoginPage
                .loginAs(GeneralSteps.createUser())
                .filterProjectList(build.name)
                .openBuild(build.name)
                .runBuild()
                .waitNumberOfBuilds(numberOfBuilds)

        assertThat(buildPage.getNumberOfBuilds())
            .withFailMessage("Number of builds should be exactly $numberOfBuilds")
            .isEqualTo(numberOfBuilds)
        assertThat(buildPage.getStartDateForBuildWithNumber(numberOfBuilds))
            .withFailMessage("The build should be equal to the created one")
            .isEqualTo(LocalDate.now())
    }
}