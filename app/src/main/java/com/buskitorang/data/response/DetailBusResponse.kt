package com.buskitorang.data.response

import com.google.gson.annotations.SerializedName

data class DetailBusResponse(

	@field:SerializedName("bus")
	val bus: DetailBus,

	@field:SerializedName("message")
	val message: String
)

data class DetailBus(

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("plate_number")
	val plateNumber: String,

	@field:SerializedName("total_seats")
	val totalSeats: Int,

	@field:SerializedName("status")
	val status: String
)
