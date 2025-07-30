package com.buskitorang.views.rute

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.buskitorang.R
import com.buskitorang.adaptor.RouteListAdapter
import com.buskitorang.data.response.BusesItem
import com.buskitorang.databinding.ActivityRuteBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RuteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRuteBinding
    private val viewModel: RuteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRuteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getDataRoute = intent.getStringExtra(DATA_ROUTE)
        viewModel.getRouteWithOrigin(getDataRoute ?: "jayapura")

        viewModel.routeResponse.observe(this){
            binding.tvRouteInfo.text = getString(R.string.tempat_holder, it.buses.get(0).origin,it.buses.get(0).destination)
            val countTotal = it.buses.size
            binding.tvResultCount.text = getString(R.string.total_rute_holder, countTotal.toString())
            setDataRoute(it.buses)
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }


    }

    private fun setDataRoute(data : List<BusesItem>) {
        val routeLayoutManager = LinearLayoutManager(this)
        binding.recyclerViewRoutes.layoutManager = routeLayoutManager
        val routeAdapter = RouteListAdapter()
        routeAdapter.submitList(data)
        binding.recyclerViewRoutes.adapter =routeAdapter

    }

    companion object {
        private const val DATA_ROUTE = "value"
    }
}