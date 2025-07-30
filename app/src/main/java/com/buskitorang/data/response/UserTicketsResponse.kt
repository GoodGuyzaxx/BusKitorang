package com.buskitorang.data.response

import com.google.gson.annotations.SerializedName

data class UserTicketsResponse(

	@field:SerializedName("UserTicketsResponse")
	val userTicketsResponse: List<UserTicketsResponseItem>
)

data class Route(

	@field:SerializedName("bus")
	val bus: Bus,

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

data class UserTicketsResponseItem(

	@field:SerializedName("route_id")
	val routeId: Int,

	@field:SerializedName("route")
	val route: Route,

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

data class Bus(

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
