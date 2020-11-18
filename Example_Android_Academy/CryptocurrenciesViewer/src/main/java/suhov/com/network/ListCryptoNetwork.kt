package suhov.com.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import suhov.com.network.models.list.CoinList
import suhov.com.utils.Constants.Companion.API_KEY
import suhov.com.utils.Constants.Companion.API_HEADER

interface ListCryptoNetwork {
    @Headers((API_HEADER+":"+API_KEY))
    @GET("map")
    fun getListFromAPI():Call<CoinList>
}