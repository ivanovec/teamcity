package rest.pojos.vcs

import com.fasterxml.jackson.annotation.JsonCreator

sealed class VcsProperty(val name: String, val value: String) {
    data class AuthType(private val type: AuthTypes) : VcsProperty(VcsProperties.AUTH.toString(), type.name)
    data class Branch(private val branch: String): VcsProperty(VcsProperties.BRANCH.toString(), branch)
    data class BranchSpec(private val spec: String): VcsProperty(VcsProperties.SPEC.toString(), spec)
    data class FetchUrl(private val url: String): VcsProperty(VcsProperties.URL.toString(), url)
    data class UserName(private val user: String): VcsProperty(VcsProperties.USER.toString(), user)
    class OtherProperty(name: String, value: String): VcsProperty(name, value)

    companion object{
        @JsonCreator
        @JvmStatic
        fun initByPropertyType(name: String, value: String): VcsProperty {
            return when (VcsProperties from name) {
                VcsProperties.AUTH -> AuthType(AuthTypes.valueOf(value))
                VcsProperties.BRANCH -> Branch(value)
                VcsProperties.SPEC -> BranchSpec(value)
                VcsProperties.URL -> FetchUrl(value)
                VcsProperties.USER -> UserName(value)
                else -> OtherProperty(name, value)
            }
        }
    }
}

enum class VcsProperties(private val prop: String){
    AUTH("authType"), BRANCH("branch"), SPEC("teamcity:branchSpec"), URL("url"), USER("user");
    override fun toString() = prop;
    companion object{
        private val map = VcsProperties.values().associateBy { it.prop }
        infix fun from(prop: String) = map[prop]
    }
}
enum class AuthTypes{
    ANONYMOUS, PASSWORD, PRIVATE_KEY_DEFAULT
}