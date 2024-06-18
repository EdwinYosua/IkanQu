package com.capstoneapp.ikanqu.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstoneapp.ikanqu.R
import com.capstoneapp.ikanqu.databinding.ActivityDetailBinding
import com.capstoneapp.ikanqu.databinding.ActivityHomeBinding
import com.capstoneapp.ikanqu.databinding.ActivityLoginBinding
import com.capstoneapp.ikanqu.ui.detail.DetailActivity
import com.capstoneapp.ikanqu.ui.login.LoginActivity
import com.capstoneapp.ikanqu.ui.main.MainActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply{
            analyzeButton.setOnClickListener{
                startActivity(Intent(this@HomeActivity, DetailActivity::class.java))
            }
            logoutButton.setOnClickListener{
                startActivity(Intent(this@HomeActivity, MainActivity::class.java))
            }
        }
    }
}