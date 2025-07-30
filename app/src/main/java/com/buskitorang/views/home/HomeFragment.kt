package com.buskitorang.views.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buskitorang.R
import com.buskitorang.databinding.FragmentHomeBinding
import com.buskitorang.views.payment.PaymentActivity
import com.buskitorang.views.payment.SnapPaymentWebActivity
import com.buskitorang.views.rute.RuteActivity

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? =null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSearch.setOnClickListener {
            requireActivity().startActivity(Intent(requireActivity(), RuteActivity::class.java))
        }

        binding.btnRouteSarmi.setOnClickListener {
            val i = Intent(requireActivity(), RuteActivity::class.java)
            i.putExtra(DATA_ROUTE, "sarmi")
            startActivity(i)
        }

        binding.btnRouteJayapura.setOnClickListener {
            val i = Intent(requireActivity(), RuteActivity::class.java)
            i.putExtra(DATA_ROUTE, "jayapura")
            startActivity(i)
        }

        binding.btnKontak.setOnClickListener {

            val i = Intent(requireActivity(), PaymentActivity::class.java)
            i.putExtra("SNAP_TOKEN","39b41eb0-8491-4eb2-be32-fb29121a8c4f")
            requireActivity().startActivity(i)
        }
    }


    companion object {
        private const val DATA_ROUTE = "value"
    }


}