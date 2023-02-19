package rest.tests

import io.qameta.allure.Feature
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import rest.pojos.vcs.VcsRoot
import rest.services.ProjectService
import rest.services.VcsService
import rest.steps.GeneralSteps

@Feature("Vcs")
@DisplayName("Vsc Tests API")
class VcsTests {
    private lateinit var vcs: VcsRoot

    @BeforeEach
    fun createVcs(){
        vcs = GeneralSteps.createProjectWithVcs()
    }

    @AfterEach
    fun deleteProject(){
        ProjectService.deleteById(vcs.project.id)
    }

    @Test
    @DisplayName("Create vcs for existing project")
    fun createVcsTest(){
        assertThat(VcsService.getById(vcs.id).decode()).isEqualTo(vcs)
    }

    @Test
    @DisplayName("Delete vcs from project")
    fun deleteVcsTest(){
        VcsService.create(vcs)
        VcsService.deleteById(vcs.id)
        VcsService.getById(vcs.id).assert().statusCode(404)
        assertThat(VcsService.getAll())
            .withFailMessage("Vcs list should not contain the deleted one")
            .doesNotContain(vcs.short())
    }

    @Test
    @DisplayName("Get all vcs")
    fun getVcsTest(){
        assertThat(VcsService.getAll())
            .withFailMessage("Vcs list should contain the created one")
            .contains(vcs.short())
    }
}