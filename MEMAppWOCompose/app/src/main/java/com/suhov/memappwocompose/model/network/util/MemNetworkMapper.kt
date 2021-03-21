package com.suhov.memappwocompose.model.network.util

import com.google.gson.annotations.SerializedName
import com.suhov.memappwocompose.model.Mem
import com.suhov.memappwocompose.model.MemNetworkEntity
import com.suhov.memappwocompose.model.util.EntityMapper

class MemNetworkMapper: EntityMapper<MemNetworkEntity, Mem> {
    override fun mapFromEntity(entity: MemNetworkEntity): Mem =
        Mem(
            id = entity.id,
            date = entity.date,
            previewURL = entity.previewURL,
            author = entity.author,
            description = entity.description,
            type = entity.type,
            videoSize = entity. videoSize,
            gifURL = entity. gifURL,
            videoPath = entity. videoPath,
            videoURL = entity. videoURL,
            fileSize = entity. fileSize,
            gifSize = entity. gifSize,
            commentsCount = entity. commentsCount,
            width = entity. width,
            votes = entity. votes,
            height = entity. height,
            canVote = entity. canVote,
            embedId = entity. embedId
        )

    override fun mapToEntity(domainModel: Mem): MemNetworkEntity =
            domainModel as MemNetworkEntity

}