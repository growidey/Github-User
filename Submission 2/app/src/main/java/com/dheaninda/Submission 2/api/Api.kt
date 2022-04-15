package com.dheaninda.part1.api

import com.dheaninda.part1.data.DetailUserResponse
import com.dheaninda.part1.data.User
import com.dheaninda.part1.data.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("username") username : String
        ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>
}
