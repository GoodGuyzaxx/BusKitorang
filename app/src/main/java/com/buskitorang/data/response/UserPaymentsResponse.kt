package com.buskitorang.data.response

import com.google.gson.annotations.SerializedName

data class UserPaymentsResponse(

	@field:SerializedName("UserPaymentResponse")
	val userPaymentResponse: List<UserPaymentResponseItem>
)

data class RoutePaymentUser(

	@field:SerializedName("bus")
	val bus: BusUserPayment,

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

data class BusUserPayment(

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

data class Ticket(

	@field:SerializedName("route_id")
	val routeId: Int,

	@field:SerializedName("route")
	val route: RoutePaymentUser,

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

data class UserPaymentResponseItem(

	@field:SerializedName("transaction_id")
	val transactionId: Any,

	@field:SerializedName("status_message")
	val statusMessage: Any,

	@field:SerializedName("amount")
	val amount: String,

	@field:SerializedName("transaction_status")
	val transactionStatus: String,

	@field:SerializedName("payment_url")
	val paymentUrl: String,

	@field:SerializedName("ticket")
	val ticket: Ticket,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("ticket_id")
	val ticketId: Int,

	@field:SerializedName("payment_type")
	val paymentType: Any,

	@field:SerializedName("bank")
	val bank: Any,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("transaction_time")
	val transactionTime: Any,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("va_number")
	val vaNumber: Any,

	@field:SerializedName("order_id")
	val orderId: String
)
