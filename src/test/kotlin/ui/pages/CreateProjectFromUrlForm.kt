package ui.pages

import com.codeborne.selenide.Selenide.`$`

object CreateProjectFromUrlForm: CreateProjectPage() {
    private val urlInput = `$`("#url")
    private val usernameInput = `$`("#username")
    private val passwordInput = `$`("#password")
    private val proceedButton = `$`("[name='createProjectFromUrl']")

    fun submitProject(url: String, user: String? = null, password: String? = null): CompleteProjectCreationPage{
        urlInput.sendKeys(url)
        if(user != null) usernameInput.sendKeys(user)
        if(password != null) passwordInput.sendKeys(password)
        proceedButton.click()
        return CompleteProjectCreationPage
    }
    override fun loadCriteria() = urlInput
}