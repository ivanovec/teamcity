package ui

import com.codeborne.selenide.Condition
import com.codeborne.selenide.SelenideElement

object ElementValidator {
    fun shouldHaveNotEmptyText(element: SelenideElement): SelenideElement {
        return element.shouldHave(Condition.matchText("(.+)"))
    }

    fun shouldHaveNotEmptyValue(element: SelenideElement): SelenideElement {
        return element.shouldHave(Condition.attributeMatching("value","(.+)"))
    }
}