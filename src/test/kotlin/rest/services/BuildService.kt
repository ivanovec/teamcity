package rest.services

import io.qameta.allure.Step
import rest.pojos.Build

object BuildService: TeamcityApi() {

    @Step("Add build config with id {id} to builds queue with REST")
    fun addToQueue(buildConfigId: String): ServiceResponse<Build>{
        return wrapResponse(
            post("/buildQueue", "{\"buildType\": {\"id\": \"$buildConfigId\"}}"),
            Build::class.java
        )
    }

    @Step("Get build with id {id} with REST")
    fun getById(buildId: String): ServiceResponse<Build>{
        return wrapResponse(get("/builds/id:$buildId"), Build::class.java)
    }

    @Step("Get all builds with REST")
    fun getAll(): List<Build>{
        return get("/builds").jsonPath().getList("build", Build::class.java)
    }

    @Step("Delete build with id {id} with REST")
    fun deleteById(buildId: String){
        delete("/builds/id:$buildId")
    }
}