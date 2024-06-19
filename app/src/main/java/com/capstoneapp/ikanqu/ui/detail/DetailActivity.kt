package com.capstoneapp.ikanqu.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstoneapp.ikanqu.databinding.ActivityDetailBinding
import com.capstoneapp.ikanqu.ui.home.HomeActivity

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            closeButton.setOnClickListener {
                startActivity(Intent(this@DetailActivity, HomeActivity::class.java))
            }
        }

    }
}