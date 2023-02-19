package rest.services

import io.qameta.allure.Step
import rest.pojos.BuildType
import rest.pojos.EntityShort

object BuildConfigService: TeamcityApi() {
    private const val PATH = "/buildTypes"

    @Step("Create build config with REST {build}")
    fun create(build: BuildType): ServiceResponse<BuildType>{
        return wrapResponse(post(PATH, build), BuildType::class.java)
    }

    @Step("Get build config with id {id} with REST")
    fun getById(id: String): ServiceResponse<BuildType>{
        return wrapResponse(get("$PATH/id:$id"), BuildType::class.java)
    }

    @Step("Get all build configs with REST")
    fun getAll(): List<EntityShort>{
        return get(PATH).jsonPath().getList("buildType", EntityShort::class.java)
    }

    @Step("Delete build config bu id {id} with REST")
    fun deleteById(id: String) = delete("$PATH/id:$id")
}