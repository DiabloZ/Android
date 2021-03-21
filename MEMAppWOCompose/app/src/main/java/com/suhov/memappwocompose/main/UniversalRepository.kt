package com.suhov.memappwocompose.main

import com.suhov.memappwocompose.util.Resource
import retrofit2.Response

interface UniversalRepository {
    suspend fun <T : Any> get(response: Response<T>): Resource<T>
}