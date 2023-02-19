package rest.generators

import rest.pojos.Project
import rest.pojos.vcs.*
import utils.IdGenerator

object VcsGenerator {
    fun default(project: Project): VcsRoot {
        val vcsProperties = arrayListOf(
            VcsProperty.AuthType(AuthTypes.PRIVATE_KEY_DEFAULT),
            VcsProperty.Branch("refs/heads/master"),
            VcsProperty.BranchSpec("refs/heads/*"),
            VcsProperty.FetchUrl("https://github.com/ivanovec/java-basics.git"),
            VcsProperty.UserName("git")
        )
        return VcsRoot("vcs${IdGenerator.idAsString()}", project, Property(vcsProperties))
    }
}