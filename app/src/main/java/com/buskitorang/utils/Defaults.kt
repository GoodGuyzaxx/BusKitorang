package com.buskitorang.utils

import com.buskitorang.data.response.Bus
import com.buskitorang.data.response.Route
import com.buskitorang.data.response.UserTicketsResponseItem

object Defaults {
    fun userTicket(): UserTicketsResponseItem = UserTicketsResponseItem(
        routeId = 0,
        route = Route(
            bus = Bus(
                updatedAt = "2025-07-23T08:32:11.000000Z",
                name = "test",
                createdAt = "2025-07-23T08:32:11.000000Z",
                id = 0,
                plateNumber = "test",
                totalSeats = 0,
                status = "test"
            ),
            arrivalTime = "2025-07-23T08:32:11.000000Z",
            updatedAt = "2025-07-23T08:32:11.000000Z",
            price = "0",
            origin = "test",
            destination = "test",
            createdAt = "2025-07-23T08:32:11.000000Z",
            id = 0,
            busId = 0,
            departureTime = "2025-07-23T08:32:11.000000Z",
            status = "test"
        ),
        updatedAt = "2025-07-23T08:32:11.000000Z",
        seatNumber = 0,
        price = "0",
        passengerId = 0,
        paymentStatus = "test",
        createdAt = "test",
        id = 0,
        status = "test"
    )
}
