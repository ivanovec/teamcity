package ui.pages

import com.codeborne.selenide.Selenide.`$`
import ui.pages.base.LoggedInPage

abstract class CreateProjectPage: LoggedInPage() {
    protected val fromUrlTab = `$`("a[href='#createFromUrl']")
    protected val manuallyTab = `$`("a[href='#createManually']")

    fun toFromUrlTab(){
        fromUrlTab.click()
    }

    fun toManuallyTab(){
        manuallyTab.click()
    }
}