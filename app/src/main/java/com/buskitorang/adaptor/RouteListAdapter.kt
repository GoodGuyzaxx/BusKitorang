package com.buskitorang.adaptor


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.buskitorang.R
import com.buskitorang.data.response.BusesItem
import com.buskitorang.databinding.ItemRouteLayoutBinding
import com.buskitorang.utils.ConvertUtils
import com.buskitorang.views.detailbus.DetailBusActivity
import com.buskitorang.views.seat.SelectSeatActivity


class RouteListAdapter: ListAdapter<BusesItem, RouteListAdapter.RouteViewHoler>(DIFF_CALLBACK)  {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RouteViewHoler {
        val binding= ItemRouteLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RouteViewHoler(binding)
    }

    override fun onBindViewHolder(
        holder: RouteViewHoler,
        position: Int
    ) {
        val item = getItem(position)
        holder.bind(item)

    }



    class RouteViewHoler(private val binding: ItemRouteLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind (data : BusesItem){
            val convertArrivalTime = ConvertUtils().convertToHourMinuteLocalDateTime(data.arrivalTime)
            val convertDepartedTime = ConvertUtils().convertToHourMinuteLocalDateTime(data.departureTime)
            val getPrice = ConvertUtils().formatToRupiah(data.price.toDouble())
            binding.apply {
                tvDestination.text= data.destination
                tvPrice.text = getPrice
                tvStatus.text = data.status
                tvDepartureTime.text = convertDepartedTime
                tvArrivalTime.text = convertArrivalTime.toString()
            }

            binding.btnDetail.setOnClickListener {
                val i = Intent(binding.root.context, DetailBusActivity::class.java)
                i.putExtra("value", data.busId)
                binding.root.context.startActivity(i)
            }

            binding.btnBook.setOnClickListener {
                val i = Intent(binding.root.context, SelectSeatActivity::class.java)
                i.putExtra("ID_ROUTE", data.id)
                binding.root.context.startActivity(i)
            }

        }

    }


    companion object {
        private val DIFF_CALLBACK =object : DiffUtil.ItemCallback<BusesItem>(){
            override fun areItemsTheSame(
                oldItem: BusesItem,
                newItem: BusesItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: BusesItem,
                newItem: BusesItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}