package com.buskitorang.views.login

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.buskitorang.R
import com.buskitorang.databinding.ActivityLoginBinding
import com.buskitorang.views.dashboard.DashboardActivity
import com.buskitorang.views.register.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel : LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getAuthData().observe(this){
            if (it.token.isNotEmpty()){
                val i = Intent(this, DashboardActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(i)
                finish()

            }
        }
        binding.btnLogin.setOnClickListener {
            if (fromValidation()) getLogin()
        }

        viewModel.responseLogin.observe(this){
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            if (it.message == "Login successful") {
                binding.btnLogin.revertAnimation()
                val i = Intent(this, DashboardActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(i)
                finish()


            } else {
                binding.btnLogin.revertAnimation()
            }
        }

        binding.tvRegisterNow.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }

    private fun getLogin(){
        val email = binding.edEmail.text.toString()
        val password = binding.edPassword.text.toString()
        viewModel.postLogin(email, password)
        binding.btnLogin.startAnimation()

    }

    private fun fromValidation() : Boolean {
        val email = binding.edEmail.text.toString()
        val password = binding.edPassword.text.toString()
        return when {
            email.isEmpty() -> {
                binding.edEmail.error = "Tidak Boleh Kosong"
                false
            }
            password.isEmpty() -> {
                binding.edPassword.error = "Tidak Boleh Kosong"
                false
            }
            else -> true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}