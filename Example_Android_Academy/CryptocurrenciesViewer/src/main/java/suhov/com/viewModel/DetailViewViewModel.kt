package suhov.com.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import suhov.com.network.models.CryptoData
import suhov.com.repository.DetailViewRepository

class DetailViewViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = DetailViewRepository(application)
    val showProgress : LiveData<Boolean>
    val cryptoList : LiveData<ArrayList<CryptoData>>
    val position:LiveData<Int>
    val graph:LiveData<Int>

    init {
        showProgress = repository.showProgress
        cryptoList = repository.cryptoList
        position = repository.positionCrypto
        graph = repository.graph
    }

    fun setStorageData(data : ArrayList<CryptoData>){
        repository.cryptoList.value = data
    }

    fun changePosition(position: Int) {
        repository.changePositionValue(position)
    }
    fun changeGraph(position: Int) {
        repository.changeGraph(position)
    }
    fun getDetailViewItem() {
        repository.getDetailView()
    }
}