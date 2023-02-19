package rest.pojos

data class UserShort(val id: Int, val username: String)
data class User(
    val username: String,
    val roles: Roles,
    val groups: Groups,
    val email: String = ""
){
    val id = 0
    var password = "test"
    fun short() = UserShort(id, username)
}
data class Role(val roleId: String, val scope: String)
data class Roles(val role: List<Role>)
data class Group(val key: String, val name: String, val description: String)
data class Groups(val group: List<Group>)