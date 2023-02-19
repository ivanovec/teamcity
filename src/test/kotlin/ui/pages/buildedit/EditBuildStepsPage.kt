package ui.pages.buildedit

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.*
import com.codeborne.selenide.SelenideElement

object EditBuildStepsPage: EditBuildConfigurationPage() {
    private val addStepLink = `$x`("//a[contains(@href, 'editRunType')]")
    private val successMessage = `$`("#unprocessed_buildRunnerSettingsUpdated")
    private val stepRows = `$$`("tr.editBuildStepRow")
    private val projectLink = `$`("[data-projectid]:last-of-type")

    fun clickAddSteps(): AddStepPageEdit {
        addStepLink.click()
        return AddStepPageEdit
    }

    fun isSucceedMessageDisplayed() = successMessage.shouldBe(Condition.visible).isDisplayed
    fun getNumberOfSteps() = stepRows.size
    fun isStepWithNameDisplayed(name: String) = getStepByName(name).shouldBe(Condition.visible).isDisplayed
    fun clickOnStepWithName(name: String): CommandStepPageEdit {
        getStepByName(name).click()
        return CommandStepPageEdit
    }

    fun getProjectId(): String{
        return projectLink.attr("data-projectid")!!
    }

    private fun getStepByName(name: String): SelenideElement {
        return `$x`("//tr[@class='editBuildStepRow' and .//strong[text()='$name']]/td")
    }
    override fun loadCriteria() = addStepLink
}