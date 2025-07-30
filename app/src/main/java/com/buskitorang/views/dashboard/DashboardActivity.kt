package com.buskitorang.views.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.buskitorang.MainActivity
import com.buskitorang.R
import com.buskitorang.databinding.ActivityDashboardBinding
import com.buskitorang.views.home.HomeFragment
import com.buskitorang.views.profile.ProfileFragment
import com.buskitorang.views.ticket.TicketFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private val viewModel: DashboardViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getAuthData().observe(this){
            if (it.token.isEmpty()){
                val i = Intent(this, MainActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(i)
                finish()
            }
        }

        loadFragment(HomeFragment())

        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId){
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.tiket -> {
                    loadFragment(TicketFragment())
                    true
                }
                R.id.profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }


    }


    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_holder, fragment)
        transaction.commit()
    }


}