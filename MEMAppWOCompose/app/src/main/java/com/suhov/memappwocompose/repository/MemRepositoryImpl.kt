package com.suhov.memappwocompose.repository

import com.suhov.memappwocompose.model.MemNetworkEntity

class MemRepositoryImpl(): MemRepository {
    override suspend fun search(description: String, query: String): List<MemNetworkEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun get(id: Int): MemNetworkEntity {
        TODO("Not yet implemented")
    }
}