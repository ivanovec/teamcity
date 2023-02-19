package ui.pages.buildedit

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.`$x`
import ui.pages.BuildConfigurationPage
import ui.pages.base.LoggedInPage

abstract class EditBuildConfigurationPage: LoggedInPage() {
    private val generalTab = `$`("#general_Tab")
    private val vcsTab = `$`("#vcsRoots_Tab")
    private val stepsTab = `$`("#runType_Tab")
    private val runButton = `$x`("//button[contains(@onclick, 'runOnAgent')]")
    private val viewModeButton  = `$`("#homeLink")


    fun toGeneralTab(){
        generalTab.click()
    }

    fun toVcsTab(){
        vcsTab.click()
    }

    fun toStepTab(): EditBuildStepsPage {
        stepsTab.click()
        return EditBuildStepsPage
    }

    fun runBuild(){

    }

    fun toViewMode(): BuildConfigurationPage {
        viewModeButton.click()
        return BuildConfigurationPage
    }
}