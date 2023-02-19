package rest.pojos

data class Project(
    val id: String,
    val name: String = id,
    val parentProjectId: String = "_Root"
){ fun short() = EntityShort(id, name) }
