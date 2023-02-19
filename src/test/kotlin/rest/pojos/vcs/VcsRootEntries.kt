package rest.pojos.vcs

import com.fasterxml.jackson.annotation.JsonProperty
import rest.pojos.EntityShort

data class VcsRootEntries(@get:JsonProperty("vcs-root-entry") val entries: MutableList<VcsRootEntry> = ArrayList()){
    fun add(id: String, vcsRoot: EntityShort): VcsRootEntries {
        entries.add(VcsRootEntry(id, vcsRoot))
        return this
    }
}

data class VcsRootEntry(val id: String, @get:JsonProperty("vcs-root") val vcsRoot: EntityShort)
