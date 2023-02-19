package config

import com.codeborne.selenide.Configuration
import java.time.Duration

object TestConfig {
    val BROWSER = setBrowser(System.getenv("BASE_URL") ?: "CHROME")
    val BASE_URL = System.getenv("BASE_URL") ?: "http://localhost:8111"
    val TEST_REPO = System.getenv("TEST_REPO") ?: "https://github.com/ivanovec/java-basics.git"
    val TOKEN = System.getenv("TOKEN") ?: ""

    val pageLoadTimeout: Duration = Duration.ofSeconds(30)
    val elementLoadTimeout: Duration = Duration.ofSeconds(10)

    private fun setBrowser(browser: String): String{
        Configuration.browser = browser
        return browser
    }
}