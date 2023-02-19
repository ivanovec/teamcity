package ui.tests

import io.qameta.allure.Feature
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import rest.pojos.BuildType
import rest.pojos.User
import rest.steps.GeneralSteps
import ui.pages.LoginPage
import ui.pages.buildedit.CommandStepPageEdit

@DisplayName("Step Tests UI")
@Feature("Step")
class StepTests: BaseTest() {
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

    @Test
    @DisplayName("Command line step is created with declared parameters")
    fun addStepTest(){
        val stepName = "echo script"
        val script = "echo \"hello world\""
        val stepsPage =
            LoginPage
            .loginAs(GeneralSteps.createUser())
            .filterProjectList(build.name)
            .openBuild(build.name)
            .toEditMode()
            .toStepTab()
            .clickAddSteps()
            .selectCommandLineStep()
            .saveCommand(stepName, script)

        assertThat(stepsPage.isSucceedMessageDisplayed())
            .withFailMessage("Success message should be displayed")
            .isTrue
        assertThat(stepsPage.getNumberOfSteps())
            .withFailMessage("Number of steps should be exactly 1")
            .isEqualTo(1)
        assertThat(stepsPage.isStepWithNameDisplayed(stepName))
            .withFailMessage("There should be step with the name")
            .isTrue

        stepsPage.clickOnStepWithName(stepName)
        assertThat(CommandStepPageEdit.isLoaded())
            .withFailMessage("Expected command step edit page is loaded")
            .isTrue
        assertThat(CommandStepPageEdit.getNameText())
            .withFailMessage("The command name should be equal to the created name")
            .isEqualTo(stepName)
        assertThat(CommandStepPageEdit.getScriptText())
            .withFailMessage("The command script should be equal to the created script")
            .isEqualTo(script)
    }
}