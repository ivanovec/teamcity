package rest.pojos

import com.fasterxml.jackson.annotation.JsonProperty
import rest.pojos.vcs.VcsRootEntries

data class BuildType(
    val id: String,
    val projectId: String,
    @get:JsonProperty("vcs-root-entries")
    val vcsRootEntries: VcsRootEntries,
    val name: String = id
)
