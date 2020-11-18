package suhov.com.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import suhov.com.network.DetailsViewInstance
import suhov.com.network.DetailsViewNetwork
import suhov.com.network.models.CryptoData
import suhov.com.network.models.current.DataItem
import suhov.com.network.models.current.GraphList
import suhov.com.utils.DateHandler
import java.util.ArrayList


class DetailViewRepository(application: Application){
    private val TAG = "DetailViewRepository"
    private val defaultCurrencies = "USD"
    private val scopeRequest = "1"
    private val dayInterval = "24"
    private val maxInterval = "2000"
    private val week = 7
    private val month = 30
    private val threeMonth = 90
    private val sixMonth = 182
    private val year = 365
    private val dayGraphValue = 0
    private val correction  = 1

    val showProgress = MutableLiveData<Boolean>()
    val graph = MutableLiveData<Int>()
    var positionCrypto = MutableLiveData<Int>()

    private val retroInstance = DetailsViewInstance
            .getDetailViewItem()
            .create(DetailsViewNetwork::class.java)

    @Volatile var cryptoList = MutableLiveData<ArrayList<CryptoData>>()

    fun changeStateProgress() {
        if (showProgress.value == null) {
            showProgress.value = true
        }
        showProgress.value = !(showProgress.value != null && showProgress.value!!)
    }

    fun changePositionValue(position: Int) {
        positionCrypto.value = position

    }

    fun changeGraph(position: Int) {
        graph.value = position
    }

    fun getDetailView() {
        val tempCryptoList = cryptoList
        changeStateProgress()

        val request = getRetroInstance()

        if (needData()) {
            request.enqueue(object : Callback<GraphList> {
                override fun onResponse(call: Call<GraphList>, response: Response<GraphList>) {
                    val data: List<DataItem> = response.body()?.data as List<DataItem>
                    for (dataItem in data) {
                        dataItem.timeConvert = DateHandler().getNewDataFormatFromInt(dataItem.time!!)
                    }
                    tempCryptoList.value!![positionCrypto.value!!] = convertDataToArrays(data, tempCryptoList.value!![positionCrypto.value!!])
                    cryptoList.value = tempCryptoList.value
                    showProgress.value = false
                }
                override fun onFailure(call: Call<GraphList>, t: Throwable) {
                    changeStateProgress()
                    Log.e(TAG, "onFailure: ", t )}
            })
        } else {changeStateProgress()}
    }

    private fun needData(): Boolean {
        if (graph.value == dayGraphValue) {
            if (!cryptoList.value!![positionCrypto.value!!].data_change_day.isNullOrEmpty()) return false
        } else {
            if (!cryptoList.value!![positionCrypto.value!!].data_change_year.isNullOrEmpty()) return false
        }
        return true
    }

    private fun getRetroInstance():Call<GraphList>{
        return if (graph.value == dayGraphValue) {
            retroInstance.getCurrentCryptoDataDay(cryptoList.value!![positionCrypto.value!!].symbol, defaultCurrencies, scopeRequest, dayInterval)
        } else {
            retroInstance.getCurrentCryptoDataOther(cryptoList.value!![positionCrypto.value!!].symbol, defaultCurrencies, scopeRequest, maxInterval)
        }
    }

    private fun convertDataToArrays(data: List<DataItem>, cryptoData: CryptoData): CryptoData {
        if (graph.value == dayGraphValue) {
            cryptoData.data_change_day = data
        } else {
            cryptoData.data_change_allTime = data
            cryptoData.data_change_year = convertData(data, year)
            cryptoData.data_change_six_month = convertData(data, sixMonth)
            cryptoData.data_change_three_month = convertData(data, threeMonth)
            cryptoData.data_change_month = convertData(data, month)
            cryptoData.data_change_week = convertData(data, week)
        }
        return cryptoData
    }

    private fun convertData(data: List<DataItem>, numberDay: Int): List<DataItem> {
        val tempData = arrayListOf<DataItem>()
        for (i in numberDay downTo 0) {
            tempData.add(data[data.size - correction - i])
        }
        return tempData
    }


}

