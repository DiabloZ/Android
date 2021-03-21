package com.suhov.memappwocompose.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class MemListNetworkEntity(
	@field:SerializedName("result")
	val result: List<MemNetworkEntity?>? = null,

	@field:SerializedName("totalCount")
	val totalCount: Int? = null
)

data class MemNetworkEntity(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("previewURL")
	val previewURL: String? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("videoSize")
	val videoSize: Int? = null,

	@field:SerializedName("gifURL")
	val gifURL: String? = null,

	@field:SerializedName("videoPath")
	val videoPath: String? = null,

	@field:SerializedName("videoURL")
	val videoURL: String? = null,

	@field:SerializedName("fileSize")
	val fileSize: Int? = null,

	@field:SerializedName("gifSize")
	val gifSize: Int? = null,

	@field:SerializedName("commentsCount")
	val commentsCount: Int? = null,

	@field:SerializedName("width")
	val width: String? = null,

	@field:SerializedName("votes")
	val votes: Int? = null,

	@field:SerializedName("height")
	val height: String? = null,

	@field:SerializedName("canVote")
	val canVote: Boolean? = null,

	@field:SerializedName("embedId")
	val embedId: String? = null
)
