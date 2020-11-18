package suhov.com.models.list

import com.google.gson.annotations.SerializedName

data class CoinList(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("status")
	val status: Status? = null
)

data class Platform(

	@field:SerializedName("symbol")
	val symbol: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("token_address")
	val tokenAddress: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)

data class DataItem(

	@field:SerializedName("symbol")
	val symbol: String? = null,

	@field:SerializedName("is_active")
	val isActive: Int? = null,

	@field:SerializedName("last_historical_data")
	val lastHistoricalData: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("rank")
	val rank: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("platform")
	val platform: Any? = null,

	@field:SerializedName("first_historical_data")
	val firstHistoricalData: String? = null
)

data class Status(

	@field:SerializedName("error_message")
	val errorMessage: Any? = null,

	@field:SerializedName("elapsed")
	val elapsed: Int? = null,

	@field:SerializedName("credit_count")
	val creditCount: Int? = null,

	@field:SerializedName("error_code")
	val errorCode: Int? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null,

	@field:SerializedName("notice")
	val notice: Any? = null
)
