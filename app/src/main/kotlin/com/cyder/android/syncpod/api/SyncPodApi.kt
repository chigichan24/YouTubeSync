package com.cyder.android.syncpod.api

import com.cyder.android.syncpod.api.request.CreateRoom
import com.cyder.android.syncpod.api.request.SignUp
import com.cyder.android.syncpod.api.response.Response
import com.cyder.android.syncpod.api.response.SearchResponse
import io.reactivex.Single
import retrofit2.http.*
import javax.inject.Singleton

/**
 * Created by chigichan24 on 2018/01/18.
 */

@Singleton
interface SyncPodApi {
    @POST("login")
    fun signIn(@Query("email") email: String, @Query("password") password: String): Single<Response>

    @POST("users")
    fun signUp(@Body user: SignUp): Single<Response>

    @GET("joined_rooms")
    fun getEnteredRooms(@Header("Authorization") token: String): Single<Response>

    @GET("rooms")
    fun getRoom(@Header("Authorization") token: String, @Query("room_key") roomKey: String): Single<Response>

    @POST("rooms")
    fun createNewRoom(@Header("Authorization") token: String, @Body room: CreateRoom): Single<Response>

    @GET("youtube/search")
    fun getYouTubeSearch(
            @Header("Authorization") token: String,
            @Query("keyword") keyword: String,
            @Query("page_token") pageToken: String?
    ): Single<SearchResponse>

    @POST("user_report")
    fun sendUserReport(@Header("Authorization") token: String, @Query("message") message: String): Single<Response>
}
