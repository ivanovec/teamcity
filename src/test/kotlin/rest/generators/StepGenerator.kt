package rest.generators

import rest.pojos.step.BuildStep
import rest.pojos.step.Property
import rest.pojos.step.StepProperty
import utils.IdGenerator

object StepGenerator {
    fun commandStep(script: String): BuildStep {
        val id = IdGenerator.idAsString()
        val properties = arrayListOf(
            StepProperty("script.content", script),
            StepProperty("teamcity.step.mode", "default"),
            StepProperty("use.custom.script", "true")
        )
        return BuildStep("step$id", id, "simpleRunner", Property(properties))
    }

    fun kotlinStep(script: String): BuildStep {
        val id = IdGenerator.idAsString()
        val properties = arrayListOf(
            StepProperty("scriptContent", script),
            StepProperty("scriptType", "custom"),
            StepProperty("teamcity.step.mode", "default"),
            StepProperty("kotlinPath", "%teamcity.tool.kotlin.compiler.bundled%")
        )
        return BuildStep("step$id", id, "kotlinScript", Property(properties))
    }
}