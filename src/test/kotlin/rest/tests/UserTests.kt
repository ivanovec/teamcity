package rest.tests

import io.qameta.allure.Feature
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import rest.generators.UserGenerator
import rest.pojos.User
import rest.services.UserService
import rest.steps.GeneralSteps
import utils.IdGenerator

@Feature("User")
@DisplayName("User Test API")
class UserTests {
    lateinit var user: User

    @BeforeEach
    fun createUser(){
        user = GeneralSteps.createUser()
    }

    @AfterEach
    fun deleteUser(){
        GeneralSteps.deleteUser(user.id)
    }

    @Test
    @DisplayName("Create admin user")
    fun createUserTest(){
        val generatedUser = UserGenerator.adminUser("test${IdGenerator.random()}")
        val createdUser = UserService.create(generatedUser).decode()
        assertThat(UserService.getById(createdUser.id).decode())
            .withFailMessage("User should be equal to created one")
            .isEqualTo(generatedUser)
    }
    @Test
    @DisplayName("Delete user")
    fun deleteUserTest(){
        val generatedUser = UserGenerator.adminUser("test${IdGenerator.random()}")
        val createdUser = UserService.create(generatedUser).decode()
        UserService.deleteUser(createdUser.id)
        UserService.getById(createdUser.id).assert().statusCode(404)
        assertThat(UserService.getAll())
            .withFailMessage("List of users should not contain the deleted one")
            .doesNotContain(createdUser.short())
    }
}