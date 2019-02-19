package ave.arch.sample.data.converter

import com.ave.arch.sample.domain.model.user.UserInfo
import ave.arch.sample.data.model.db.user.UserEntity
import ave.arch.sample.data.model.network.user.UserResponse
import ave.arch.sample.data.utils.getOrDie

object UserConverter {

    fun fromNetwork(source: UserResponse): UserInfo {
        return UserInfo(
                id = getOrDie(source.id, "id"),
                firstName = getOrDie(source.firstName, "firstName"),
                lastName = getOrDie(source.lastName, "lastName")
        )
    }

    fun toDatabase(source: UserInfo): UserEntity {
        return UserEntity(
                id = source.id,
                firstName = source.firstName,
                lastName = source.lastName
        )
    }

    fun fromDatabase(source: UserEntity): UserInfo {
        return UserInfo(
                id = source.id,
                firstName = source.firstName,
                lastName = source.lastName
        )
    }
}