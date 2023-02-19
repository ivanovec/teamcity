package rest.services

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import config.TestConfig
import io.qameta.allure.restassured.AllureRestAssured
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.config.ObjectMapperConfig
import io.restassured.config.RestAssuredConfig
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.http.ContentType
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification

sealed class TeamcityApi {
    companion object {
        init {
            RestAssured.filters(AllureRestAssured(), RequestLoggingFilter(), ResponseLoggingFilter())
            RestAssured.config = RestAssuredConfig.config().objectMapperConfig(
                ObjectMapperConfig().jackson2ObjectMapperFactory { _, _ -> getObjectMapper() }
            )
        }

        private fun getObjectMapper() =
            ObjectMapper().registerKotlinModule().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }
    private val requestSpec: RequestSpecification =
        RequestSpecBuilder()
            .setBaseUri("${TestConfig.BASE_URL}/app/rest")
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .addHeader("Authorization","Bearer ${TestConfig.TOKEN}")
            .build()

    protected fun post(url: String, body: Any):Response = RestAssured.given().spec(requestSpec).body(body).post(url)
    protected fun get(url: String):Response = RestAssured.given().spec(requestSpec).get(url)

    protected fun delete(url: String):Response = RestAssured.given().spec(requestSpec).delete(url)

    protected fun <T> wrapResponse(response: Response, responseClass: Class<T>) = ServiceResponse(response, responseClass)
}