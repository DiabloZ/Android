package suhov.com.network.models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import suhov.com.network.models.current.GraphList

interface DetailsViewNetwork {
    @GET("histohour")
    fun getCurrentCryptoData(
            @Query("fsym")coinSymbol: String,
            @Query("tsym")currency:String,
            @Query("aggregate")writeOne:String,
            @Query("limit") limitHour:String
    ): Call<GraphList>
}