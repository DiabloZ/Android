package com.suhov.memappwocompose.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class MemList(
	@field:SerializedName("result")
	val result: List<MemNetworkEntity?>? = emptyList(),

	@field:SerializedName("totalCount")
	val totalCount: Int? = null
)
@Entity(tableName = "MemDB")
@Parcelize
data class Mem(

	@PrimaryKey
	val id: Int? = null,
	val date: String? = null,
	val previewURL: String? = null,
	val author: String? = null,
	val description: String? = null,
	val type: String? = null,
	val videoSize: Int? = null,
	val gifURL: String? = null,
	val videoPath: String? = null,
	val videoURL: String? = null,
	val fileSize: Int? = null,
	val gifSize: Int? = null,
	val commentsCount: Int? = null,
	val width: String? = null,
	val votes: Int? = null,
	val height: String? = null,
	val canVote: Boolean? = null,
	val embedId: String? = null
) : Parcelable
