package ui.pages.base

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide
import ui.pages.LoginPage

abstract class LoggedInPage: BasePage() {
    private val userMenuButton = Selenide.`$`("[data-hint-container-id='header-user-menu']")
    private val logoutButton = Selenide.`$x`("//div[contains(@id, 'logout')]")

    fun logout(): LoginPage {
        userMenuButton.shouldBe(Condition.visible).click()
        logoutButton.shouldBe(Condition.visible).click()
        return LoginPage
    }
}