package ui.pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$`
import config.TestConfig
import ui.pages.base.LoggedInPage

object ProjectsPage: LoggedInPage() {
    private val createProjectButton = `$`("[data-test-link-with-icon='add']")
    private val searchProjectInput = `$`("#search-projects")

     fun createProject(): CreateProjectFromUrlForm{
         createProjectButton.shouldBe(Condition.visible, TestConfig.elementLoadTimeout).click()
         return CreateProjectFromUrlForm
     }

    fun filterProjectList(filter: String): ProjectsPage{
        searchProjectInput.sendKeys(filter)
        return this
    }
    fun openProject(name: String): ProjectsPage {
        `$`("[data-project-id='$name']").click()
        return ProjectsPage
    }

    fun openBuild(name: String): BuildConfigurationPage {
          `$`("span[aria-label='$name']").click()
        return BuildConfigurationPage
    }

    override fun loadCriteria() = createProjectButton
}