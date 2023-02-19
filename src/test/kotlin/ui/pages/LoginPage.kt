package ui.pages

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By
import rest.pojos.User
import ui.pages.base.BasePage

object LoginPage: BasePage() {
    private val loginInput: SelenideElement = `$`("#username")
    private val passwordInput: SelenideElement = `$`("#password")
    private val rememberCheckbox: SelenideElement = `$`("#password")
    private val loginButton: SelenideElement = `$`("input[name=submitLogin]")

    fun loginAs(login: String, password: String, isRemember: Boolean = false): ProjectsPage{
        loginInput.sendKeys(login)
        passwordInput.sendKeys(password)
        if(isRemember.xor(isRememberChecked())){
            rememberCheckbox.click()
        }
        loginButton.click()
        return ProjectsPage
    }

    fun loginAs(user: User): ProjectsPage {
        loginAs(user.username, user.password)
        return ProjectsPage
    }

    private fun isRememberChecked(): Boolean{
        val atrClass = rememberCheckbox.find(By.xpath("./../span")).attr("class")
        return atrClass?.contains("custom-checkbox_checked") ?: false
    }

    override fun loadCriteria() = loginButton
}