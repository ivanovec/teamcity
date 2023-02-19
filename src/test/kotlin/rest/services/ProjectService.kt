package rest.services

import io.qameta.allure.Step
import rest.pojos.EntityShort
import rest.pojos.Project

object ProjectService: TeamcityApi() {
    private const val PATH = "/projects"

    @Step("Create project {project} with REST")
    fun create(project: Project): ServiceResponse<Project>{
        return wrapResponse(post(PATH, project), Project::class.java)
    }

    @Step("get project by id {id} with REST")
    fun getById(id: String): ServiceResponse<Project>{
        return wrapResponse(get("$PATH/id:$id"), Project::class.java)
    }

    @Step("get all projects with REST")
    fun getAll(): List<EntityShort>{
        return get(PATH).jsonPath().getList("project", EntityShort::class.java)
    }

    @Step("delete project by id {id} with REST")
    fun deleteById(id: String) = delete("$PATH/id:$id")
}