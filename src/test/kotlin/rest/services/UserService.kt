package rest.services

import io.qameta.allure.Step
import rest.pojos.User
import rest.pojos.UserShort

object UserService: TeamcityApi() {
    private const val PATH = "/users"

    @Step("Create user {user} with REST")
    fun create(user: User): ServiceResponse<User>{
        return wrapResponse(post(PATH, user), User::class.java)
    }

    @Step("Get user by id {id} with REST")
    fun getById(id: Int): ServiceResponse<User>{
        return wrapResponse(get("$PATH/id:$id"), User::class.java)
    }

    @Step("Get all users with REST")
    fun getAll(): List<UserShort>{
        return get(PATH).jsonPath().getList("user", UserShort::class.java)
    }

    @Step("Delete user with id {id} with REST")
    fun deleteUser(id: Int) = delete("$PATH/id:$id")
}