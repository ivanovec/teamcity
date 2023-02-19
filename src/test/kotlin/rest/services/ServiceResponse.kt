package rest.services

import io.restassured.response.Response
import io.restassured.response.ValidatableResponse

class ServiceResponse<T>(private val response: Response, private val clazz: Class<T>) {

    fun decode(): T = response.`as`(clazz)
    fun assert(): ValidatableResponse = response.then()
    fun raw() = response
}