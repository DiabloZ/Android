package suhov.com.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import suhov.com.network.ListCryptoInstance
import suhov.com.network.ListCryptoNetwork
import suhov.com.network.models.CryptoData
import suhov.com.network.models.list.CoinList
import suhov.com.utils.Constants
import suhov.com.utils.DateHandler
import java.util.*

class ListCryptoRepository(application: Application) {
    private val TAG = "ContentValues"
    private val dataJsonList = "data"
    private val quoteJsonList = "quote"
    private val currenciesJsonList = "USD"
    private val priceJsonList = "price"
    private val changeHourJsonList = "percent_change_1h"
    private val changeDayJsonList = "percent_change_24h"
    private val changeWeekJsonList = "percent_change_7d"
    private val dataJsonImg = "Data"
    private val imageURLJsonImg = "ImageUrl"
    private val lastUpdate = "last_updated"
    private val safeQuery = "1"
    private val loadLimit = 10

    val showProgress = MutableLiveData<Boolean>()
    @Volatile var cryptoList = MutableLiveData<ArrayList<CryptoData>>()

    fun changeStateProgress() {
        showProgress.value = !(showProgress.value != null && showProgress.value!!)
    }

    fun getCryptoList() {
        changeStateProgress()
        val tempCryptoList = ArrayList<CryptoData>()

        val retroInstance = ListCryptoInstance
                .getRetrofitInstanceCryptoList()
                .create(ListCryptoNetwork::class.java)
        val request = retroInstance.getListFromAPI()

        request.enqueue(object : Callback<CoinList> {
            override fun onResponse(call: Call<CoinList>, response: Response<CoinList>) {
                val dataList = response.body()?.data
                for (data in dataList!!) {
                    val cryptoData = CryptoData()
                    cryptoData.id = data?.id.toString()
                    cryptoData.name = data?.name.toString()
                    cryptoData.symbol = data?.symbol.toString()
                    tempCryptoList.add(cryptoData)
                }
                cryptoList.value = tempCryptoList
                changeStateProgress()
            }

            override fun onFailure(call: Call<CoinList>, t: Throwable) {
                Log.e(TAG, "onFailure: ", t)
                changeStateProgress()
            }
        })
    }

    fun getDataOfList() {
        changeStateProgress()
        CoroutineScope(IO).launch {
            val tempCryptoList = cryptoList

            var query = ""
            var counter = 0
            for (string in cryptoList.value!!) {
                query += string.id + ","
                counter++
                if (counter == loadLimit) break
            }
            query += safeQuery

            val request = Request.Builder()
                    .url(Constants.CRYPTO_DATA_URL + query)
                    .header(Constants.API_HEADER, Constants.API_KEY)
                    .build()

            val client = OkHttpClient()

            val response:okhttp3.Response = client.newCall(request).execute()

            val jsonResponse = response.body()?.string() as String
            val jsonObject = JSONObject(jsonResponse)
            val jsonData = jsonObject.getJSONObject(dataJsonList)

            for (entry in tempCryptoList.value!!) {
                if (jsonData.has(entry.id) && !jsonData.isNull(entry.id)) {
                    val jsonCryptoItem = jsonData.getJSONObject(entry.id)
                    val jsonQuote = jsonCryptoItem.getJSONObject(quoteJsonList)
                    val jsonUSD = jsonQuote.getJSONObject(currenciesJsonList)

                    entry.price = jsonUSD.getDouble(priceJsonList)
                    entry.percent_change_1h = jsonUSD.getDouble(changeHourJsonList)
                    entry.percent_change_24h = jsonUSD.getDouble(changeDayJsonList)
                    entry.percent_change_7d = jsonUSD.getDouble(changeWeekJsonList)

                    entry.last_updated = DateHandler().getNewDataFormatFromString(jsonUSD.getString(lastUpdate))
                }
            }
            withContext(Main){
                cryptoList.value = tempCryptoList.value
                changeStateProgress()
            }
        }
    }

    fun getImgOfList() {
        CoroutineScope(IO).launch {
            val tempCryptoList = cryptoList

            val request = Request.Builder()
                    .url(Constants.IMG_URL)
                    .build()

            val client = OkHttpClient()

            val response:okhttp3.Response = client.newCall(request).execute()

            val jsonRepsone = response.body()?.string()
            val jsonObject = JSONObject(jsonRepsone)
            val jsonData = jsonObject.getJSONObject(dataJsonImg)

            for (entry in tempCryptoList.value!!) {
                if (jsonData.has(entry.symbol) && !jsonData.isNull(entry.symbol)) {
                    val jsonCryptoItem = jsonData.getJSONObject(entry.symbol)
                    if (jsonCryptoItem.has(imageURLJsonImg) && !jsonCryptoItem.isNull(imageURLJsonImg)) {
                        entry.imgURL = jsonCryptoItem.getString(imageURLJsonImg)
                    }
                }
            }
            withContext(Main){cryptoList.value = tempCryptoList.value}
        }
    }
}

