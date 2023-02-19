package rest.tests

import io.qameta.allure.Feature
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import rest.pojos.BuildType
import rest.pojos.EntityShort
import rest.services.BuildConfigService
import rest.steps.GeneralSteps

@Feature("Build Config")
@DisplayName("Build Config Tests API")
class BuildConfigTests {
    private lateinit var build: BuildType

    @BeforeEach
    fun createBuildConfig(){
        build = GeneralSteps.createProjectWithBuildConfig()
    }

    @AfterEach
    fun deleteProject(){
        GeneralSteps.deleteProject(build.projectId)
    }

    @Test
    @DisplayName("Create build config")
    fun createBuildConfigTest(){
        assertThat(BuildConfigService.getById(build.id).decode())
            .withFailMessage("Build config data with test id should be equal to created config")
            .isEqualTo(build)
    }

    @Test
    @DisplayName("Delete build config")
    fun deleteBuildConfigTest(){
        BuildConfigService.deleteById(build.id)
        BuildConfigService.getById(build.id).assert().statusCode(404)
        assertThat(BuildConfigService.getAll())
            .withFailMessage("List of build configs should not contain deleted config")
            .doesNotContain(EntityShort(build.id, build.name))
    }

    @Test
    @DisplayName("Get all build configs")
    fun getBuildConfigTest(){
        assertThat(BuildConfigService.getAll())
            .withFailMessage("List of build configs should contain created config")
            .contains(EntityShort(build.id, build.name))
    }
}