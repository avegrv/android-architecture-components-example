package ave.arch.sample.data.network.api.user

import io.reactivex.Single
import retrofit2.http.GET
import ave.arch.sample.data.model.network.user.UserResponse

interface UserApi {

    @GET("avegrv/json-templates/master/user.json")
    fun getUserInfo(): Single<UserResponse>
}