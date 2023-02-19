package ui.pages.buildedit

import com.codeborne.selenide.Selenide.`$`

object GeneralBuildConfigurationPage: EditBuildConfigurationPage() {
    private val nameInput = `$`("#name")
    override fun loadCriteria() = nameInput
}