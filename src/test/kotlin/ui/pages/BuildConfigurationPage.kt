package ui.pages

import com.codeborne.selenide.CollectionCondition
import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.*
import com.codeborne.selenide.SelenideElement
import config.TestConfig
import ui.pages.base.LoggedInPage
import ui.pages.buildedit.GeneralBuildConfigurationPage
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object BuildConfigurationPage: LoggedInPage() {
    private val editModeButton  = `$x`("//a[contains(@href, 'editBuild')]")
    private val runButton  = `$`("[data-test='run-build']")
    private val builds = `$$`("[data-build-id]  button[aria-label~='Build']")

    fun toEditMode(): GeneralBuildConfigurationPage{
        editModeButton.click()
        return GeneralBuildConfigurationPage
    }

    fun runBuild(): BuildConfigurationPage{
        runButton.click()
        return this
    }

    fun getStartDateForBuildWithNumber(number: Int): LocalDate{
        val datePattern = "dd MMM yy HH:mm"
        val textElement = getBuildRowByNumber(number).`$x`(".//span[contains(@class, 'exactStarted')]")
        val text = textElement.shouldBe(Condition.visible, TestConfig.pageLoadTimeout).text
        return LocalDate.parse(text, DateTimeFormatter.ofPattern(datePattern))
    }

    fun getNumberOfBuilds(): Int{
        return builds.size
    }

    fun waitNumberOfBuilds(number: Int): BuildConfigurationPage {
        builds.shouldHave(CollectionCondition.size(number))
        return  BuildConfigurationPage
    }

    private fun getBuildRowByNumber(number: Int): SelenideElement{
        return `$x`("//*[@data-build-id][.//button[@aria-label = 'Build #$number' ]]")
    }

    override fun loadCriteria() = editModeButton
}