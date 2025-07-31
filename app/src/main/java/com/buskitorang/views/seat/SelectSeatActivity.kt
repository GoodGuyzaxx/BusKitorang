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

//        viewModel.getBusDetail(getBusId)
//        viewModel.busResponse.observe(this){
//            binding.tvBusInfo.text = it.bus.name
//        }


        binding.btnBack.setOnClickListener {
            onBackPressed()
        }


            val getRouteID = intent.getIntExtra("ID_ROUTE",0)

        viewModel.getTicketsByRoute(getRouteID)


        viewModel.ticketsResponse.observe(this){
            if (it.isEmpty()){

            }
            viewModel.setOccupiedSeatsFromApi(it)
        }


        setupSeatButtons()
//



    }

    private fun setupSeatButtons() {
        // Add all seat buttons to list for easy management
        for (i in 1..19) {
            val seatId = resources.getIdentifier("seat_$i", "id", packageName)
            val seatButton = findViewById<Button>(seatId)
            seatButtons.add(seatButton)
            viewModel.occupiedSeats.observe(this) {
                if (it.isNotEmpty()) {
                    if (it.contains(i)){
                        seatButton.background = ContextCompat.getDrawable(this@SelectSeatActivity, R.drawable.seat_occupied)
                        seatButton.isEnabled = false
                        seatButton.tag = "occupied"
                    }
                }
            }
            seatButton.setOnClickListener { view ->
                handleSeatSelection(view as Button, i)
            }
        }
    }

    private fun handleSeatSelection(seatButton: Button, seatNumber: Int) {
        val seatStatus = seatButton.tag as String

        if (seatStatus == "occupied") {
            Toast.makeText(this, "Kursi sudah terisi", Toast.LENGTH_SHORT).show()
            return
        }

        // Clear previous selection
        clearSeatSelection()

        // Select new seat
        if (seatStatus == "available") {
            selectedSeat = seatNumber
            seatButton.background = ContextCompat.getDrawable(this, R.drawable.seat_selected)
            seatButton.setTextColor(ContextCompat.getColor(this, android.R.color.white))
            seatButton.tag = "selected"

            // Show selected seat info
            binding.cardSelectedSeat.visibility = View.VISIBLE
            binding.tvSelectedSeat.text = "Kursi $seatNumber"

            updateUI()
        }
    }

    private fun clearSeatSelection() {
        selectedSeat?.let { seatNum ->
            val seatId = resources.getIdentifier("seat_$seatNum", "id", packageName)
            val seatButton = findViewById<Button>(seatId)

            if (seatButton.tag == "selected") {
                seatButton.background = ContextCompat.getDrawable(this, R.drawable.seat_available)
                seatButton.setTextColor(ContextCompat.getColor(this, android.R.color.black))
                seatButton.tag = "available"
            }
        }
        selectedSeat = null
        binding.cardSelectedSeat.visibility = View.GONE
    }

    private fun updateUI() {
        val getBusId = intent.getIntExtra("value",0)
        val totalPrice = if (selectedSeat != null) getBusId else 0
        binding.tvTotalPrice.text = "Rp ${NumberFormat.getNumberInstance(Locale("id", "ID")).format(totalPrice)}"
        binding.btnContinue.isEnabled = selectedSeat != null
    }


}