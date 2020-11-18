package suhov.com.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import suhov.com.utils.Constants

class ListCryptoNetwork {
    companion object{
        fun getRetrogitInstanceCryptoList(): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(Constants.CRYPTO_LIST_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }
}