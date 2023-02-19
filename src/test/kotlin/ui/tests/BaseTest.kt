package ui.tests

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.open
import com.codeborne.selenide.logevents.SelenideLogger
import config.TestConfig
import io.qameta.allure.selenide.AllureSelenide
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach


abstract class BaseTest {
    companion object{
        init {
            SelenideLogger.addListener("AllureSelenide", AllureSelenide())
        }
    }
    @BeforeEach
    fun openUrl() {
        open(TestConfig.BASE_URL)
    }

    @AfterEach
    fun closeBrowser() {
        Selenide.closeWebDriver()
    }
}