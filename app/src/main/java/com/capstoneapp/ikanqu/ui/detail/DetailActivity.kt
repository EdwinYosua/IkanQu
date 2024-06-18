package com.capstoneapp.ikanqu.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstoneapp.ikanqu.R
import com.capstoneapp.ikanqu.databinding.ActivityDetailBinding
import com.capstoneapp.ikanqu.databinding.ActivityLoginBinding
import com.capstoneapp.ikanqu.ui.home.HomeActivity
import com.capstoneapp.ikanqu.ui.register.RegisterActivity

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply{
            closeButton.setOnClickListener{
                startActivity(Intent(this@DetailActivity, HomeActivity::class.java))
            }
        }

    }
}