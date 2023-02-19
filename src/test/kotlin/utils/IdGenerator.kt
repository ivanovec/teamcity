package utils

import org.apache.commons.lang3.RandomStringUtils
import java.util.*

object IdGenerator {
    fun idAsString(count: Int = 10) = "a${RandomStringUtils.randomAlphanumeric(count)}"
    fun random() = UUID.randomUUID().mostSignificantBits
}