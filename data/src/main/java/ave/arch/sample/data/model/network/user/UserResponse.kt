package ave.arch.sample.data.model.network.user

import com.google.gson.annotations.SerializedName

data class UserResponse(
        @SerializedName("id")
        val id: Long?,

        @SerializedName("first_name")
        val firstName: String?,

        @SerializedName("last_name")
        val lastName: String?
)