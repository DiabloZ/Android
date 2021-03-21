package com.suhov.memappwocompose.main

import com.suhov.memappwocompose.model.MemListNetworkEntity
import com.suhov.memappwocompose.model.network.MemApi
import com.suhov.memappwocompose.util.Resource
import javax.inject.Inject

class DefaultMainRepositoryInstance @Inject constructor(private val api:MemApi): UniversalRepositoryInstance(){

    suspend fun getList(result:(Resource<MemListNetworkEntity>) -> Unit){
        result(get(api.getMemes()))
    }




}