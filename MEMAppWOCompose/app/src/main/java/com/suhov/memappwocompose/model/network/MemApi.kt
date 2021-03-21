package com.suhov.memappwocompose.model.network

import com.suhov.memappwocompose.model.MemListNetworkEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MemApi {

    @GET("latest/{page}?")
    suspend fun getMemes(
            @Path("page") page:Int = 0,
            @Query("json") jsonFormat:Boolean = true,
            @Query("pageSize") pageSize:Int = 50,
            @Query("types") types:String = "gif,coub"
            ): Response<MemListNetworkEntity>
}