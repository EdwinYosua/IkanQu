package com.capstoneapp.ikanqu.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.capstoneapp.ikanqu.R
import com.capstoneapp.ikanqu.ViewModelFactory
import com.capstoneapp.ikanqu.databinding.ActivityHomeBinding
import com.capstoneapp.ikanqu.ui.detail.DetailActivity
import com.capstoneapp.ikanqu.ui.main.MainActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
    private val homeViewModel: HomeViewModel by viewModels { factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = intent.getStringExtra(EXTRA_NAME) ?: "404"

        binding.apply {

            imageLabelTextView.text =
                getString(R.string.hallo_text, userName.split(" ").firstOrNull() ?: userName)

            analyzeButton.setOnClickListener {
//                startActivity(Intent(this@HomeActivity, DetailActivity::class.java))
            }
            logoutButton.setOnClickListener {
                homeViewModel.logout()
                startActivity(Intent(this@HomeActivity, MainActivity::class.java))
                finish()
            }
        }
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
    }
}