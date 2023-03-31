package pt.isel.ls.tasks.api.routers.users.models

import kotlinx.serialization.Serializable
import pt.isel.ls.tasks.api.ISerializable
import pt.isel.ls.tasks.domain.User

@Serializable
data class UserInfoDTO(val id: Int, val name: String, val email: String){
    companion object{
       operator fun invoke(user: User) = UserInfoDTO(user.id, user.name, user.email)
    }
}
