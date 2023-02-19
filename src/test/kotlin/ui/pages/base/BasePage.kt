package ui.pages.base

import com.codeborne.selenide.Condition
import com.codeborne.selenide.SelenideElement
import config.TestConfig

abstract class BasePage {
    protected abstract fun loadCriteria(): SelenideElement
    fun isLoaded() = loadCriteria().shouldBe(Condition.visible, TestConfig.pageLoadTimeout).isDisplayed
}