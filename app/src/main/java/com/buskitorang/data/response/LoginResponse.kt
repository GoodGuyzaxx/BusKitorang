package com.buskitorang.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("passenger")
	val passenger: LoginPassenger,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("token")
	val token: String
)

data class LoginPassenger(

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
