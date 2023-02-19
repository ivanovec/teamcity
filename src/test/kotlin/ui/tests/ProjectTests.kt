package ui.tests

import config.TestConfig
import io.qameta.allure.Feature
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import rest.pojos.User
import rest.services.ProjectService
import rest.steps.GeneralSteps
import ui.pages.CompleteProjectCreationPage
import ui.pages.LoginPage
import ui.pages.buildedit.EditBuildStepsPage

@DisplayName("Project Tests UI")
@Feature("Project")
class ProjectTests: BaseTest() {
    private var projectName: String? = null
    private lateinit var user: User

    @BeforeEach
    fun createUser(){
        user = GeneralSteps.createUser()
    }

    @AfterEach
    fun deleteProject(){
        GeneralSteps.deleteUser(user.id)
        val projects = ProjectService.getAll()
        projects.filter { it.name == projectName }.forEach{ GeneralSteps.deleteProject(it.id) }
    }

    @Test
    @DisplayName("Create project from git url leads to build configuration page")
    fun createProjectFromUrlTest(){
        val repositoryDataPage =
            LoginPage
            .loginAs(user)
            .createProject()
            .submitProject(TestConfig.TEST_REPO)

            projectName = repositoryDataPage.getProjectName()
            repositoryDataPage.create()
            .getProjectId()

        assertThat(EditBuildStepsPage.isLoaded())
            .withFailMessage("Expected that edit step page was loaded")
            .isTrue
    }

    @Test
    @DisplayName("Default settings for project vcs")
    fun defaultValuesForProjectFromUrl(){
        val defaultBranch = "refs/heads/master"
        val defaultRefSpec = "refs/heads/*"

        LoginPage
            .loginAs(user)
            .createProject()
            .submitProject(TestConfig.TEST_REPO)

        assertThat(CompleteProjectCreationPage.isLoaded())
            .withFailMessage("Expected that create project page was loaded")
            .isTrue
        assertThat(CompleteProjectCreationPage.getDefaultBranchText())
            .withFailMessage("Default branch should be")
            .isEqualTo(defaultBranch)
        assertThat(CompleteProjectCreationPage.getBranchSpecText())
            .withFailMessage("Default ref spec should be")
            .isEqualTo(defaultRefSpec)
    }
}