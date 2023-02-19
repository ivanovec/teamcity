package rest.services

import io.qameta.allure.Step
import rest.pojos.EntityShort
import rest.pojos.vcs.VcsRoot

object VcsService: TeamcityApi() {
    private const val PATH = "/vcs-roots"

    @Step("Create vcs {vcs} with REST")
    fun create(vcs: VcsRoot): ServiceResponse<VcsRoot>{
        return wrapResponse(post(PATH, vcs), VcsRoot::class.java)
    }

    @Step("Get vcs by {id} with REST")
    fun getById(id: String): ServiceResponse<VcsRoot>{
        return wrapResponse(get("$PATH/id:$id"), VcsRoot::class.java)
    }

    @Step("Get all vcs with REST")
    fun getAll(): List<EntityShort>{
        return get(PATH).jsonPath().getList("vcs-root", EntityShort::class.java)
    }

    @Step("Delete vcs by id {id} with REST")
    fun deleteById(id: String) = delete("$PATH/id:$id")
}