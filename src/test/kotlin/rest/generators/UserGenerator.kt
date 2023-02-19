package rest.generators

import rest.pojos.*

object UserGenerator {
    fun adminUser(name: String): User{
        return User(name, adminRole(), defaultGroup())
    }

    private fun adminRole() = Roles(arrayListOf(Role("SYSTEM_ADMIN", "g")))
    private fun defaultGroup() = Groups(arrayListOf(
        Group("ALL_USERS_GROUP", "All Users", "Contains all TeamCity users")
    ))
}