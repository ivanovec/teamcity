package ui.tests

import io.qameta.allure.Feature
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import rest.pojos.User
import rest.steps.GeneralSteps
import ui.pages.LoginPage

@DisplayName("Authorization Tests UI")
@Feature("Login")
class LoginTests: BaseTest() {
    private lateinit var user: User

    @BeforeEach
    fun createUser(){
        user = GeneralSteps.createUser()
    }

    @AfterEach
    fun deleteUser(){
        GeneralSteps.deleteUser(user.id)
    }

    @Test
    @DisplayName("Login with correct credentials leeds to project page")
    fun loginWithRightCredsTest(){
        val projectPage = LoginPage.loginAs(user.username, user.password)
        assertThat(projectPage.isLoaded())
            .withFailMessage("Expected that project page was loaded")
            .isTrue
    }

    @Test
    @DisplayName("Logout leeds to login page")
    fun logout(){
        LoginPage.loginAs(user.username, user.password).logout()
        assertThat(LoginPage.isLoaded())
            .withFailMessage("Expected that login page was loaded")
            .isTrue
    }
}