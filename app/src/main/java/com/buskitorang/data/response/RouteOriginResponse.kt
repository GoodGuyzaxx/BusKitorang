package com.buskitorang.data.response

import com.google.gson.annotations.SerializedName

data class RouteOriginResponse(

	@field:SerializedName("buses")
	val buses: List<BusesItem>,

	@field:SerializedName("message")
	val message: String
)

data class BusesItem(

	@field:SerializedName("arrival_time")
	val arrivalTime: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("price")
	val price: String,

	@field:SerializedName("origin")
	val origin: String,

	@field:SerializedName("destination")
	val destination: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("bus_id")
	val busId: Int,

	@field:SerializedName("departure_time")
	val departureTime: String,

	@field:SerializedName("status")
	val status: String
)
