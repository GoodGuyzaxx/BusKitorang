package com.buskitorang.views.detailbus

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.buskitorang.R
import com.buskitorang.databinding.ActivityDetailBusBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailBusActivity : AppCompatActivity() {
    private lateinit var binding:  ActivityDetailBusBinding
    private val viewModel: DetailBusViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getDataId = intent.getIntExtra(DATA_ID,0)
        viewModel.getBusDetail(getDataId)

        viewModel.busResponse.observe(this){
            binding.tvBusName.text = it.bus.name
            binding.tvBusLicense.text = it.bus.plateNumber
            binding.tvBusCapacity.text = it.bus.totalSeats.toString()
            binding.tvBusStatus.text = it.bus.status
        }

        binding.btnBack.setOnClickListener{
            onBackPressed()
        }

    }


    companion object {
        private const val DATA_ID = "value"
    }
}