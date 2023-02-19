package rest.tests

import io.qameta.allure.Feature
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import rest.pojos.Project
import rest.services.ProjectService
import rest.steps.GeneralSteps

@Feature("Project")
@DisplayName("Project Test API")
class ProjectTests {
    private lateinit var createdProject: Project

    @BeforeEach
    fun createProject(){
        createdProject = GeneralSteps.createProject()
    }

    @AfterEach
    fun deleteProject(){
        GeneralSteps.deleteProject(createdProject.id)
    }

    @Test
    @DisplayName("Create project")
    fun createProjectTest(){
        val responseProject = ProjectService.getById(createdProject.id).decode()
        assertThat(responseProject)
            .withFailMessage("Project should be equal to the created one")
            .isEqualTo(createdProject)
    }

    @Test
    @DisplayName("Delete project")
    fun deleteProjectTest(){
        ProjectService.deleteById(createdProject.id)
        ProjectService.getById(createdProject.id).assert().statusCode(404)
        assertThat(ProjectService.getAll())
            .withFailMessage("List of project should not contain the deleted one")
            .doesNotContain(createdProject.short())
    }

    @Test
    @DisplayName("Get all projects")
    fun getProjectsTest(){
        val currentProjects = ProjectService.getAll()
        assertThat(currentProjects)
            .withFailMessage("List of project should contain the created one")
            .contains(createdProject.short())
    }
}