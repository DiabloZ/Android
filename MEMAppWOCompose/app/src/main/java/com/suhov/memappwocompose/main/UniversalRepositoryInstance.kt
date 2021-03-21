package com.suhov.memappwocompose.main

import android.util.Log
import com.suhov.memappwocompose.util.Resource
import retrofit2.Response
import java.lang.Exception

open class UniversalRepositoryInstance : UniversalRepository{
    override suspend fun <T:Any> get(response: Response<T>): Resource<T> =
        try {
            val result = response.body()

            if (response.isSuccessful && result != null){
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception){
            Resource.Error(e.message ?: "An error occurred")
        }

}