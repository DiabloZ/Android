package com.suhov.memappwocompose.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suhov.memappwocompose.model.MemNetworkEntity
import com.suhov.memappwocompose.model.MemListNetworkEntity
import com.suhov.memappwocompose.util.DispatcherProvider
import com.suhov.memappwocompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
        private val repository: DefaultMainRepositoryInstance,
        private val dispatchers: DispatcherProvider
) : ViewModel() {

    sealed class MemEvent<out T>{
        class Success<T: Any>(val result: T): MemEvent<T>()
        class Failure(val errorText:String): MemEvent<Nothing>()
        object  Loading: MemEvent<Nothing>()
        object Empty: MemEvent<Nothing>()
    }

    val _mutbleList = MutableStateFlow<List<MemNetworkEntity>>(emptyList())
    val mutbleList: StateFlow<List<MemNetworkEntity>> = _mutbleList

    val _mutableList = MutableStateFlow<MemEvent<List<MemNetworkEntity>>>(MemEvent.Empty)
    val mutableList: StateFlow<MemEvent<List<MemNetworkEntity>>> = _mutableList
    private val _conversion = MutableStateFlow<MemEvent<MemListNetworkEntity>>(MemEvent.Empty)
    val conversion: StateFlow<MemEvent<MemListNetworkEntity>> = _conversion

    fun requireListMemes(){
        Log.i("TAGTAGTAG", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
        viewModelScope.launch(dispatchers.io) {
            _conversion.value = MemEvent.Loading
            repository.getList {  memList ->
                when (memList){
                    is Resource.Error<*> -> _conversion.value = MemEvent.Failure(memList.message!!)
                    is Resource.Success<*> -> {
                        val memes = memList.data
                        if (memes == null){
                            _conversion.value = MemEvent.Failure("Unexpected error")
                        } else {

                            _conversion.value = MemEvent.Success(memes)
                            //_mutableList.value = MemEvent.Success(memes.result as List<MemNetworkEntity>)
                        }
                    }
                }
            }
        }
    }

    fun addField() {
        viewModelScope.launch(dispatchers.io) {
            /*val test1 = mutableList.value
            _mutableList.value =  MemEvent.Success(listOf<Mem>(, listOf(Mem(id = 0, author = "test", description = "!!!!!!!!!!!!!"))))
            */
            _mutbleList.value += listOf(MemNetworkEntity(id = _mutbleList.value.size, author = "test", description = "!!!!!!!!!!!!!", embedId = null))
        }
    }
}