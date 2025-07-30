package com.buskitorang.views.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.buskitorang.R
import com.buskitorang.databinding.FragmentProfileBinding
import com.buskitorang.views.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import taimoor.sultani.sweetalert2.Sweetalert

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private  var _binding: FragmentProfileBinding? =null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getProfileData()

        viewModel.getAuthData().observe(viewLifecycleOwner){data->
            if (data.token.isNotEmpty()){
                binding.btnLogout.setOnClickListener {
                    logoutAlert(data.token)
                }
            }
        }

    }

    private fun getProfileData(){
        viewModel.getAuthData().observe(viewLifecycleOwner) { data ->
            binding.tvUserName.text = data.name.toString()
            binding.tvUserPhone.text = data.phone.toString()
            binding.tvUserEmail.text = data.email.toString()
        }
    }

    private fun logoutAlert(token : String){
        val sweetAlert = Sweetalert(requireActivity(), Sweetalert.WARNING_TYPE).apply {
            setTitleText("Yakin Mau Logout?")
            setCancelText("Logout")
            setCancelClickListener {
                viewModel.postLogout("Bearer ${token}")
                val i = Intent(requireActivity(), LogoutRedirectActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(i)
                requireActivity().finish()
            }
            show()
        }
        sweetAlert
    }

}