package com.buskitorang.views.register

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.buskitorang.databinding.ActivityRegisterBinding
import com.buskitorang.views.login.LoginActivity
import com.buskitorang.views.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import taimoor.sultani.sweetalert2.Sweetalert

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private val viewModel : RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDaftar.setOnClickListener {
            if (formValidation()) {
                postRegister()
            }
        }


        viewModel.registerResponse.observe(this){
            Toast.makeText(this, "${it.message}",Toast.LENGTH_SHORT).show()
            if (it.message == "Akun Berhail Dibuat") alertDialog()
            Log.d("TAG", "onCreate: ${it.message}")
        }



    }

    private fun postRegister(){
        val name = binding.edName.text.toString()
        val email = binding.edEmail.text.toString()
        val phone = binding.edNomorHp.text.toString()
        val password = binding.edPassword.text.toString()

        viewModel.postRegister(name,email,phone,password)
    }

    private fun alertDialog(){
        val sweetAlert = Sweetalert(this, Sweetalert.SUCCESS_TYPE).apply {
            setTitleText("Akun Berhasil Dibuat")
            setContentText("Silakahkan Lakukan Login")
            setCancelText("Login")
            setCancelClickListener {
                val i = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(i)
                finish()
            }
            show()
        }
        sweetAlert
    }

    private fun formValidation(): Boolean{
        val name = binding.edName.text.toString()
        val email = binding.edEmail.text.toString()
        val phone = binding.edNomorHp.text.toString()
        val password = binding.edPassword.text.toString()
        return when{
            name.isEmpty() ->{
                binding.edName.error = "Tidak Boleh Kosong"
                false
            }
            email.isEmpty() -> {
                binding.edEmail.error = "Tidak Boleh Kosong"
                false
            }
            phone.isEmpty() -> {
                binding.edNomorHp.error = "Tidak Boleh Kosong"
                false
            }
            password.isEmpty() -> {
                binding.edPassword.error ="Tidak Boleh Kosong"
                false
            }
            else -> {
                true
            }
        }
    }
}