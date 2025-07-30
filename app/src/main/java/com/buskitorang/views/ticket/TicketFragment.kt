package com.buskitorang.views.ticket

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.buskitorang.R
import com.buskitorang.adaptor.TicketsListAdapter
import com.buskitorang.data.response.Bus
import com.buskitorang.data.response.Route
import com.buskitorang.data.response.UserTicketsResponseItem
import com.buskitorang.databinding.FragmentTicketBinding
import com.buskitorang.utils.Defaults
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TicketFragment : Fragment() {

    private var _binding : FragmentTicketBinding? = null
    private val binding get() = _binding!!
    private val viewModel : TicketViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTicketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.getAuth().observe(viewLifecycleOwner) { auth ->
            val token = auth.token
            viewModel.getUserTickets("Bearer $token")
        }


        viewModel.userTicketResponse.observe(viewLifecycleOwner){
            if (it.size == 0){
                binding.rvTiketList.visibility = View.GONE
                binding.LayoutHolderTiketEmpty.visibility = View.VISIBLE
            } else {
                setUpRecycleView(it)
            }
        }



    }

    private fun setUpRecycleView(data : List<UserTicketsResponseItem>){
        val ticketLayoutManager = LinearLayoutManager(requireActivity())
        binding.rvTiketList.layoutManager =ticketLayoutManager
        val ticketAdapter = TicketsListAdapter()
        ticketAdapter.submitList(data)
        binding.rvTiketList.adapter = ticketAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // hindari memory leak
    }
}