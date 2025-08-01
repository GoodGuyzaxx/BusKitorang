package com.buskitorang.views.redirectToPayment

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView


import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.buskitorang.R
import com.buskitorang.views.payment.PaymentActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentConfirm: DialogFragment() {
    private lateinit var textSets : TextView
//    private lateinit var textTujuan : TextView
//    private lateinit var textWaktuKeberangaktan  : TextView
    private lateinit var textTotalHarga : TextView
    private lateinit var btnConfirm : Button
    private val viewModel: PaymentConfirmViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_confirm, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textSets = view.findViewById(R.id.tvSeatNumber)
//        textTujuan = view.findViewById(R.id.tvRoute)
//        textWaktuKeberangaktan = view.findViewById(R.id.tvTime)
        textTotalHarga = view.findViewById(R.id.tvTotalPrice)
        btnConfirm = view.findViewById(R.id.btnConfirm)

        val dataSeats = arguments?.getInt("SEAT_VALUE") ?: 0
        val dataRoute = arguments?.getInt("ID_ROUTE") ?: 0

        textSets.text = dataSeats.toString()



        viewModel.getAuth().observe(viewLifecycleOwner){
            val token = "Bearer ${it.token}"
            btnConfirm.setOnClickListener {
                viewModel.postPayment(token,dataRoute,dataSeats)
                viewModel.paymentResponse.observe(this) {data->
                    if (data.message == "Ticket booked successfully"){
                        val getID = data.ticket.id
                        val i = Intent(requireActivity(), PaymentActivity::class.java)
                        i.putExtra("WAKTU_BERANGKAT",data.ticket.route.departureTime )
                        i.putExtra("WAKTU_TIBA",data.ticket.route.arrivalTime )
                        i.putExtra("RUTE_AWAL", data.ticket.route.origin)
                        i.putExtra("RUTE_TIBA", data.ticket.route.destination)
                        i.putExtra("ID_VALUE", getID)
                        requireContext().startActivity(i)
                        requireActivity().finish()
                    }
                }
            }
        }


    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

}