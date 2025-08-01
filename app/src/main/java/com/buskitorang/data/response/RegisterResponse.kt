package com.buskitorang.data.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("passenger")
	val passenger: RegisterPassenger? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class RegisterPassenger(

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null
)
