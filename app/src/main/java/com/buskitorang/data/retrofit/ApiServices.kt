package com.buskitorang.data.retrofit


import com.buskitorang.data.response.DetailBusResponse
import com.buskitorang.data.response.LoginResponse
import com.buskitorang.data.response.LogoutResponse
import com.buskitorang.data.response.PaymentResponse
import com.buskitorang.data.response.RegisterResponse
import com.buskitorang.data.response.RouteOriginResponse
import com.buskitorang.data.response.TicketsByRouteResponse
import com.buskitorang.data.response.TicketsByRouteResponseItem
import com.buskitorang.data.response.UserPaymentResponseItem

import com.buskitorang.data.response.UserTicketsResponseItem
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServices
{
    @FormUrlEncoded
    @POST("passenger/login")
    @Headers("Accept: application/json")
    suspend fun getLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse
    @FormUrlEncoded
    @POST("passenger/register")
    @Headers("Accept: application/json")
    suspend fun postRegister(
        @Field("name") name : String,
        @Field("email") email : String,
        @Field("phone") phone : String,
        @Field("password") password : String
    ): RegisterResponse


    @POST("passenger/logout")
    @Headers("Accept: application/json")
    suspend fun postLogout(
        @Header("Authorization") token: String,
        ): LogoutResponse


    @GET("routes/origin/{route}")
    @Headers("Accept: application/json")
    suspend fun getRouteWithOrigin(
        @Path("route") route:String
    ): RouteOriginResponse


    @GET("buses/{id}")
    @Headers("Accept: application/json")
    suspend fun getBusDetail(
        @Path("id") id : Int
    ): DetailBusResponse

    @GET("tickets/route/{id}")
    @Headers("Accept: application/json")
    suspend fun getTicketsByRoute(
        @Path("id") id : Int
    ): List<TicketsByRouteResponseItem>

    @GET("passenger/tickets")
    @Headers("Accept: application/json")
    suspend fun getUserTickets(
        @Header("Authorization")token : String
    ): List<UserTicketsResponseItem>

    @GET("payments/user/{id}")
    @Headers("Accept: application/json")
    suspend fun getUserPayments(
        @Path("id") id : Int
    ): List<UserPaymentResponseItem>



    @FormUrlEncoded
    @POST("passenger/tickets")
    @Headers("Accept: application/json")
    suspend fun postPayment(
        @Header("Authorization") token :String,
        @Field("route_id") routeId : Int,
        @Field("seat_number") seatNumber : Int
    ): PaymentResponse


}