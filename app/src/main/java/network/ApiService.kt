package network

import models.User
import models.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("users")
    fun createUser(@Body user: User): Call<UserResponse>

    @GET("users/{email}")
    fun getUser(@Path("email") email: String): Call<User>
}