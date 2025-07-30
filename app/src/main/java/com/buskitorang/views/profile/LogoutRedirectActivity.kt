package com.buskitorang.views.profile

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.buskitorang.MainActivity
import com.buskitorang.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogoutRedirectActivity : AppCompatActivity() {
    private val viewModel : ProfileViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout_redirect)

        viewModel.getLogout()

        viewModel.getAuthData().observe(this){
            if (it.token.isEmpty()){
                val i = Intent(this, MainActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(i)
                finish()
            }
        }
    }
}