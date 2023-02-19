package ui.pages.buildedit

import com.codeborne.selenide.Selenide.`$`
import ui.ElementValidator


object CommandStepPageEdit: EditBuildConfigurationPage() {
    private val stepNameInput = `$`("#buildStepName")
    private val scriptText = `$`("#script\\.content")
    private val saveButton = `$`("#saveButtons [name='submitButton']")

    fun saveCommand(name: String, script: String): EditBuildStepsPage {
        stepNameInput.sendKeys(name)
        scriptText.sendKeys(script)
        saveButton.click()
        return EditBuildStepsPage
    }

    fun getNameText(): String {
        return ElementValidator.shouldHaveNotEmptyValue(stepNameInput).attr("value")!!
    }

    fun getScriptText(): String {
        return ElementValidator.shouldHaveNotEmptyValue(scriptText).attr("value")!!
    }
    override fun loadCriteria() = stepNameInput
}