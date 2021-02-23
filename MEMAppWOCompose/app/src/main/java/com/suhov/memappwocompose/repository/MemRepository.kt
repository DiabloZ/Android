package com.suhov.memappwocompose.repository

import com.suhov.memappwocompose.model.Mem

interface MemRepository {
    suspend fun search(description:String, query:String): List<Mem>

    suspend fun get(id: Int): Mem
}