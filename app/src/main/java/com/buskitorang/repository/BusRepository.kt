package com.buskitorang.repository

import com.buskitorang.data.response.DetailBusResponse
import com.buskitorang.data.response.LoginResponse
import com.buskitorang.data.response.LogoutResponse
import com.buskitorang.data.response.RegisterResponse
import com.buskitorang.data.response.RouteOriginResponse
import com.buskitorang.data.response.TicketsByRouteResponse
import com.buskitorang.data.response.UserTicketsResponse
import com.buskitorang.data.response.UserTicketsResponseItem
import com.buskitorang.data.retrofit.ApiServices

class BusRepository(private val apiServices: ApiServices) {

    suspend fun postRegister(name: String,email : String, phone: String, password : String): RegisterResponse{
        return apiServices.postRegister(name,email,phone, password)
    }

    suspend fun postLogin(email: String, password:String) : LoginResponse {
        return apiServices.getLogin(email,password)
    }

    suspend fun postLogout(token: String): LogoutResponse{
        return apiServices.postLogout(token)
    }

    suspend fun getRouteWithOrigin(route: String): RouteOriginResponse{
        return apiServices.getRouteWithOrigin(route)
    }

    suspend fun getBusDetail(id : Int): DetailBusResponse{
        return  apiServices.getBusDetail(id)
    }

    suspend fun getTicketsByRoute(id: Int): TicketsByRouteResponse {
        return apiServices.getTicketsByRoute(id)
    }

    suspend fun getUserTickets(token: String): List<UserTicketsResponseItem> {
        return apiServices.getUserTickets(token)
    }
}