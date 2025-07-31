package com.buskitorang.views.seat

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.buskitorang.R
import com.buskitorang.data.response.TicketsByRouteResponseItem
import com.buskitorang.databinding.ActivitySelectSeatBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat
import java.util.Locale


@AndroidEntryPoint
class SelectSeatActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectSeatBinding
    private val seatButtons = mutableListOf<Button>()
    private var selectedSeat: Int? = null
    private val viewModel: SeatViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectSeatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getBusId = intent.getIntExtra("value",0)
        val getTiba = intent.getStringExtra("TIBA") ?: ""
        val getBerangkat = intent.getStringExtra("BERANGKAT") ?: ""

        binding.tvRouteInfo.text = getString(R.string.tempat_holder, getBerangkat, getTiba)

        viewModel.getBusDetail(getBusId)
        viewModel.busResponse.observe(this){
            binding.tvBusInfo.text = it.bus.name
        }



        binding.btnContinue.setOnClickListener {
            val getRouteID = intent.getIntExtra("ID_ROUTE",0)

            viewModel.getTicketsByRoute(getRouteID)
        }

//        viewModel.ticketsResponse.observe(this){
//            if (it.ticketsByRouteResponse. == "unpaid") {
//                binding.seat1.background = ContextCompat.getDrawable(this,R.drawable.seat_occupied)
//            }
//            getStatusSeat(it.ticketsByRouteResponse.get(0).paymentStatus)
//            Toast.makeText(this@SelectSeatActivity, "${it.ticketsByRouteResponse.get(0).paymentStatus}", Toast.LENGTH_LONG).show()
//        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

//        getSeatStatus()

//        setupSeatButtons()

    }

    private fun setupSeatButtons() {
        // Add all seat buttons to list for easy management
        for (i in 1..19) {
            val seatId = resources.getIdentifier("seat_$i", "id", packageName)
            val seatButton = findViewById<Button>(seatId)
            seatButtons.add(seatButton)


            seatButton.apply {
                background = ContextCompat.getDrawable(this@SelectSeatActivity,R.drawable.seat_occupied)
            }

        }
    }

    private fun getStatusSeat(status : String){
        for (i in 1..19) {
            val seatId = resources.getIdentifier("seat_$i", "id", packageName)
            val seatButton = findViewById<Button>(seatId)
            seatButtons.add(seatButton)
            viewModel.ticketsResponse.observe(this){data ->

                if (status == "unpaid"){
                    seatButton.apply {
                        background = ContextCompat.getDrawable(this@SelectSeatActivity, R.drawable.seat_available)
                    }
                }

            }
        }
    }



}