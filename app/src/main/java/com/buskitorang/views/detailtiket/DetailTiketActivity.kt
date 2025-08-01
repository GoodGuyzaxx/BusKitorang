package com.buskitorang.views.detailtiket

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.buskitorang.R
import com.buskitorang.databinding.ActivityDetailTiketBinding
import com.buskitorang.databinding.ActivityPaymentBinding
import com.buskitorang.utils.ConvertUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailTiketActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailTiketBinding
    private val viewModel : DetailTiketViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTiketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getPaymentID = intent.getIntExtra("ID_VALUE",0)
        viewModel.getUserPayment(getPaymentID)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        setUpUiData()

    }

    private fun setUpUiData(){
        viewModel.paymentResponse.observe(this){ data ->
            val convertAmout = ConvertUtils().formatToRupiah(data.first().amount.toDouble() )
            val convertKeberangatan = ConvertUtils().convertToHourMinuteLocalDateTime(data.first().ticket.route.departureTime)
            val convertTiba = ConvertUtils().convertToHourMinuteLocalDateTime(data.first().ticket.route.arrivalTime)
            val convertTanggal = ConvertUtils().formatTanggalIndonesia(data.first().ticket.createdAt)
            data.first().apply {
                binding.tvTiketOrederId.text = resources.getString(R.string.tiket_order_id_holder, orderId)
                binding.tvBusNane.text = ticket.route.bus.name
                binding.tvBusPlatNumber.text =ticket.route.bus.plateNumber
                binding.tvSeatNumber.text =ticket.seatNumber.toString()
                binding.tvAmount.text =convertAmout
                if (ticket.paymentStatus == "paid") binding.tvPaymentStatus.text = "Selesai"
                binding.tvKeberangaktan.text = convertKeberangatan
                binding.tvTiba.text = convertTiba
                binding.tvTanggalTiketDibeli.text = convertTanggal
                binding.tvTotalBus.text = ticket.route.bus.totalSeats.toString()
                binding.tvStatusRoute.text = ticket.route.status
                binding.tvDestinationPlace.text = ticket.route.destination
                binding.tvOriginPlace.text = ticket.route.origin
            }
        }
        viewModel.getAuthData().observe(this){ auth ->
            binding.tvName.text = auth.name
            binding.tvEmailUser.text =auth.email
            binding.tvPhone.text = auth.phone
        }
    }


}