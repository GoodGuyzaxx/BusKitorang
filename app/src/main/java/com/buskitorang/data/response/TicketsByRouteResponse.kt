package com.buskitorang.data.response

import com.google.gson.annotations.SerializedName

data class TicketsByRouteResponse(

	@field:SerializedName("TicketsByRouteResponse")
	val  ticketsByRouteResponse: List<TicketsByRouteResponseItem>
)

data class TicketsByRouteResponseItem(

	@field:SerializedName("route_id")
	val routeId: Int,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("seat_number")
	val seatNumber: Int,

	@field:SerializedName("price")
	val price: String,

	@field:SerializedName("passenger_id")
	val passengerId: Int,

	@field:SerializedName("payment_status")
	val paymentStatus: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("status")
	val status: String
)
