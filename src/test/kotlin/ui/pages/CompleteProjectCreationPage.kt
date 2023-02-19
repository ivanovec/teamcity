package ui.pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$`
import config.TestConfig
import ui.ElementValidator
import ui.pages.base.LoggedInPage
import ui.pages.buildedit.EditBuildStepsPage

object CompleteProjectCreationPage: LoggedInPage() {
    private val defaultBranch = `$`("#branch")
    private val branchSpec = `$`("#teamcity\\:branchSpec")

    private val projectName = `$`("#projectName")
    private val buildTypeName = `$`("#buildTypeName")
    private val proceedButton = `$`("[name='createProject']")
    private val cancelButton = `$`(".saveButtonsBlock a.btn.cancel")

    fun create(): EditBuildStepsPage {
        proceedButton.shouldBe(Condition.visible, TestConfig.elementLoadTimeout).click()
        return EditBuildStepsPage
    }

    fun getDefaultBranchText(): String {
        return ElementValidator.shouldHaveNotEmptyValue(defaultBranch).attr("value")!!
    }

    fun getBranchSpecText(): String {
        return ElementValidator.shouldHaveNotEmptyText(branchSpec).text
    }

    fun getProjectName(): String {
        return ElementValidator.shouldHaveNotEmptyValue(projectName).attr("value")!!
    }

    override fun loadCriteria() = projectName
}