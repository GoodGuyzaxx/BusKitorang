package com.buskitorang.data.response

import com.google.gson.annotations.SerializedName

data class UserPaymentsResponse(

	@field:SerializedName("UserPaymentsResponse")
	val userPaymentsResponse: List<UserPaymentsResponseItem>
)

data class Passenger(

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("email")
	val email: String
)

data class Ticket(

	@field:SerializedName("route_id")
	val routeId: Int,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("passenger")
	val passenger: Passenger,

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

data class UserPaymentsResponseItem(

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
