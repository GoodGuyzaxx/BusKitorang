package com.buskitorang.adaptor


import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.buskitorang.R
import com.buskitorang.data.response.UserTicketsResponseItem
import com.buskitorang.databinding.ItemTiketLayoutBinding
import com.buskitorang.utils.ConvertUtils
import com.buskitorang.views.detailtiket.DetailTiketActivity
import com.buskitorang.views.payment.PaymentActivity

class TicketsListAdapter: ListAdapter<UserTicketsResponseItem, TicketsListAdapter.TicketViewHolder>(DIFF_CALLBACK){
    class TicketViewHolder(private val binding: ItemTiketLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data : UserTicketsResponseItem){
            val converter = ConvertUtils()
            binding.apply {
                tvBusSeat.text = data.seatNumber.toString()
                tvBookingDate.text = binding.root.context.getString(R.string.holer_dipesan, converter.convertToDayMonthYear(data.createdAt))
                tvDepartureTime.text = converter.convertToHourMinuteLocalDateTime(data.route.departureTime)
                tvDepartureLocation.text = data.route.origin
                tvArrivalTime.text = converter.convertToHourMinuteLocalDateTime(data.route.arrivalTime)
                tvArrivalLocation.text = data.route.destination
                tvBusName.text = data.route.bus.name
            }

            if (data.paymentStatus == "unpaid"){
                binding.tvTicketStatus.text = "Belum Bayar"
                binding.tvTicketStatus.background = binding.root.context.getDrawable(R.drawable.seat_occupied)
                binding.btnAction.text = "Bayar"
                binding.btnAction.setOnClickListener {
                    val i = Intent(binding.root.context, PaymentActivity::class.java)
                    i.putExtra("ID_VALUE", data.id)
                    i.putExtra("WAKTU_BERANGKAT",data.route.departureTime )
                    i.putExtra("WAKTU_TIBA",data.route.arrivalTime )
                    i.putExtra("RUTE_AWAL", data.route.origin)
                    i.putExtra("RUTE_TIBA", data.route.destination)
                    binding.root.context.startActivity(i)
                }

            }else if (data.paymentStatus == "paid"){
                binding.tvTicketStatus.text = "Selesai"
                binding.tvTicketStatus.background = binding.root.context.getDrawable(R.drawable.seat_available)
                binding.btnAction.setOnClickListener {
                    val i = Intent(binding.root.context, DetailTiketActivity::class.java)
                    i.putExtra("ID_VALUE", data.id)
                    i.putExtra("WAKTU_BERANGKAT",data.route.departureTime )
                    i.putExtra("WAKTU_TIBA",data.route.arrivalTime )
                    i.putExtra("RUTE_AWAL", data.route.origin)
                    i.putExtra("RUTE_TIBA", data.route.destination)
                    binding.root.context.startActivity(i)
                }
            }else {
                binding.tvTicketStatus.text = "Pending"
                binding.tvTicketStatus.background = binding.root.context.getDrawable(R.drawable.seat_pending)
                binding.btnAction.text = "Lanjutkan"
                binding.btnAction.setOnClickListener {
                    val i = Intent(binding.root.context, PaymentActivity::class.java)
                    i.putExtra("ID_VALUE", data.id)
                    i.putExtra("WAKTU_BERANGKAT",data.route.departureTime )
                    i.putExtra("WAKTU_TIBA",data.route.arrivalTime )
                    i.putExtra("RUTE_AWAL", data.route.origin)
                    i.putExtra("RUTE_TIBA", data.route.destination)
                    binding.root.context.startActivity(i)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TicketViewHolder {
        val binding = ItemTiketLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TicketViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TicketViewHolder,
        position: Int
    ) {
        val item = getItem(position)
        Log.d("TicketsListAdapter", "bind pos=$position item=$item")
        holder.bind(item)
    }

    companion object {
        private val DIFF_CALLBACK =object : DiffUtil.ItemCallback<UserTicketsResponseItem>(){
            override fun areItemsTheSame(
                oldItem: UserTicketsResponseItem,
                newItem: UserTicketsResponseItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: UserTicketsResponseItem,
                newItem: UserTicketsResponseItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

}