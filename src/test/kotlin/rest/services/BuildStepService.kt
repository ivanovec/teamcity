package rest.services

import io.qameta.allure.Step
import rest.pojos.EntityShort
import rest.pojos.step.BuildStep

object BuildStepService: TeamcityApi() {

    private fun path(buildId: String) = "/buildTypes/id:$buildId/steps"

    @Step("Create step {step} for build config with id {buildId} with REST")
    fun create(step: BuildStep, buildId: String): ServiceResponse<BuildStep>{
        return wrapResponse(post(path(buildId), step), BuildStep::class.java)
    }

    @Step("Get by id {buildId} for build config {buildId} with REST")
    fun getById(id: String, buildId: String): ServiceResponse<BuildStep>{
        return wrapResponse(get("${path(buildId)}/$id"), BuildStep::class.java)
    }

    @Step("Get all steps for build config with id {buildId} with REST")
    fun getAll(buildId: String): List<EntityShort>{
        return get(path(buildId)).jsonPath().getList("step", EntityShort::class.java)
    }

    @Step("Delete step {id} from build with id {buildId} with REST")
    fun deleteById(id: String, buildId: String) = delete("${path(buildId)}/$id")
}