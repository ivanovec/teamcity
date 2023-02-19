package rest.pojos.step

import rest.pojos.EntityShort

data class BuildStep(val id:String, val name: String, val type:String, val properties: Property){
    fun short() = EntityShort(id, name)
}
data class Property(val property: List<StepProperty>)
data class StepProperty(val name: String, val value: String)