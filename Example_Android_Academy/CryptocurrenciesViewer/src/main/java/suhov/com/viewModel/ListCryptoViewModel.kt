package suhov.com.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import suhov.com.network.models.CryptoData
import suhov.com.repository.ListCryptoRepository

class ListCryptoViewModel(application: Application): AndroidViewModel(application) {
    private val repository = ListCryptoRepository(application)
    val showProgress : LiveData<Boolean>
    val cryptoList : LiveData<ArrayList<CryptoData>>

    init {
        this.showProgress = repository.showProgress
        this.cryptoList = repository.cryptoList
    }

    fun getCryptoList() {
        repository.getCryptoList()
    }

    fun getDataOfList() {
        repository.getDataOfList()
    }

    fun getImgOfList() {
        repository.getImgOfList()
    }
}