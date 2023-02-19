package rest.pojos.vcs

import rest.pojos.EntityShort
import rest.pojos.Project

data class VcsRoot(
    val id: String,
    val project: Project,
    val properties: Property,
    val name: String = id,
    val vcsName: String = "jetbrains.git",
){ fun short() = EntityShort(id, name) }
data class Property(val property: List<VcsProperty>)