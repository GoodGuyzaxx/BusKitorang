package com.buskitorang.data.response

import com.google.gson.annotations.SerializedName

data class PaymentResponse(

	@field:SerializedName("ticket")
	val ticket: TicketPayment,

	@field:SerializedName("payment")
	val payment: Payment,

	@field:SerializedName("message")
	val message: String
)

data class PassengerPayment(

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

data class TicketPayment(

	@field:SerializedName("route_id")
	val routeId: Int,

	@field:SerializedName("route")
	val route: RoutePayment,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("passenger")
	val passenger: PassengerPayment,

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

data class RoutePayment(

	@field:SerializedName("bus")
	val bus: BusPayment,

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

data class BusPayment(

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

data class Payment(

	@field:SerializedName("amount")
	val amount: String,

	@field:SerializedName("transaction_status")
	val transactionStatus: String,

	@field:SerializedName("payment_url")
	val paymentUrl: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("ticket_id")
	val ticketId: Int,

	@field:SerializedName("order_id")
	val orderId: String
)
