package com.suhov.memappwocompose.repository

import com.suhov.memappwocompose.model.Mem

class MemRepositoryImpl(): MemRepository {
    override suspend fun search(description: String, query: String): List<Mem> {
        TODO("Not yet implemented")
    }

    override suspend fun get(id: Int): Mem {
        TODO("Not yet implemented")
    }
}