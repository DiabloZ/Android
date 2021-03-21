package com.suhov.memappwocompose.repository

import com.suhov.memappwocompose.model.MemNetworkEntity

interface MemRepository {
    suspend fun search(description:String, query:String): List<MemNetworkEntity>

    suspend fun get(id: Int): MemNetworkEntity
}