package suhov.com.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import suhov.com.network.models.current.GraphList
import suhov.com.network.models.list.CoinList
import suhov.com.utils.Constants
import suhov.com.utils.Constants.Companion.API_KEY
import suhov.com.utils.Constants.Companion.API_HEADER

interface CryptoNetwork {
    @Headers((API_HEADER+":"+API_KEY))
    @GET("map")
    fun getListFromAPI():Call<CoinList>

    @GET("histohour")
    fun getCurrentCryptoData(
            @Query("fsym")coinSymbol: String,
            @Query("tsym")currency:String,
            @Query("aggregate")writeOne:String,
            @Query("limit") limitHour:String
    ):Call<GraphList>

}