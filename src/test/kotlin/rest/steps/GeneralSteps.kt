package rest.steps

import rest.generators.StepGenerator
import rest.generators.UserGenerator
import rest.generators.VcsGenerator
import rest.pojos.BuildType
import rest.pojos.EntityShort
import rest.pojos.Project
import rest.pojos.User
import rest.pojos.vcs.VcsRoot
import rest.pojos.vcs.VcsRootEntries
import rest.services.*
import utils.IdGenerator

//should be divided by business domain in real life
object GeneralSteps {
    fun createProject(): Project{
        return ProjectService.create(
            Project("project${IdGenerator.idAsString()}")
        ).decode()
    }

    fun createUser(name: String = "test${IdGenerator.idAsString()}", password: String? = null): User {
        val generatedUser = UserGenerator.adminUser(name)
        if(password != null) generatedUser.password = password
        val createdUser  = UserService.create(generatedUser).decode()
        createdUser.password = generatedUser.password
        return createdUser
    }

    fun createProjectWithVcs(): VcsRoot{
        val project = createProject()
        val vcs = VcsGenerator.default(project)
        VcsService.create(vcs)
        return vcs
    }

    fun createProjectWithBuildConfig(): BuildType{
        val vcs = createProjectWithVcs()
        val build = BuildType(
            "build${IdGenerator.idAsString()}",
            vcs.project.id,
            VcsRootEntries().add(vcs.id, EntityShort(vcs.id, vcs.name))
        )

        BuildConfigService.create(build)
        return build
    }

    fun createProjectWithCommandStep(command: String = "echo \"Hello World!\""): BuildType{
        val vcs = createProjectWithVcs()
        val build = BuildType(
            "build${IdGenerator.idAsString()}",
            vcs.project.id,
            VcsRootEntries().add(vcs.id, EntityShort(vcs.id, vcs.name))
        )

        BuildConfigService.create(build)
        BuildStepService.create(StepGenerator.commandStep(command), build.id)

        return build
    }

    fun deleteProject(id: String){
        ProjectService.deleteById(id)
    }

    fun deleteUser(id: Int){
        UserService.deleteUser(id)
    }
}