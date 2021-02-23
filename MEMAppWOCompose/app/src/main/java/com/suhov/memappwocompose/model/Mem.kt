package com.suhov.memappwocompose.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class MemList(
	@field:SerializedName("result")
	val result: List<Mem?>? = emptyList(),

	@field:SerializedName("totalCount")
	val totalCount: Int? = 0
)
@Entity(tableName = "MemDB")
data class Mem(

	@PrimaryKey
	@field:SerializedName("id")
	val id: Int? = 0,

	@field:SerializedName("date")
	val date: String? = "",

	@field:SerializedName("previewURL")
	val previewURL: String? = "",

	@field:SerializedName("author")
	val author: String? = "",

	@field:SerializedName("description")
	val description: String? = "",

	@field:SerializedName("type")
	val type: String? = "",

	@field:SerializedName("videoSize")
	val videoSize: Int? = 0,

	@field:SerializedName("gifURL")
	val gifURL: String? = "",

	@field:SerializedName("videoPath")
	val videoPath: String? = "",

	@field:SerializedName("videoURL")
	val videoURL: String? = "",

	@field:SerializedName("fileSize")
	val fileSize: Int? = 0,

	@field:SerializedName("gifSize")
	val gifSize: Int? = 0,

	@field:SerializedName("commentsCount")
	val commentsCount: Int? = 0,

	@field:SerializedName("width")
	val width: String? = "",

	@field:SerializedName("votes")
	val votes: Int? = 0,

	@field:SerializedName("height")
	val height: String? = "",

	@field:SerializedName("canVote")
	val canVote: Boolean? = false,

	@field:SerializedName("embedId")
	val embedId: String? = ""
)
