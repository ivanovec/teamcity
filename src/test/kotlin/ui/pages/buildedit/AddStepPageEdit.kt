package ui.pages.buildedit

import com.codeborne.selenide.Selenide.`$`

object AddStepPageEdit: EditBuildConfigurationPage() {
    private val searchInput = `$`("[data-test='runner-item-filter']")
    private val commandLineStep = `$`("[data-test='runner-item simpleRunner']")

    fun selectCommandLineStep(): CommandStepPageEdit {
        commandLineStep.click()
        return CommandStepPageEdit
    }
    override fun loadCriteria() = searchInput
}