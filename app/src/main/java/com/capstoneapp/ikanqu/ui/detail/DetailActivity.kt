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

        val prediction = intent.getStringExtra(EXTRA_PREDICT) ?: "404"

        binding.apply {

            titleTextView.text = prediction

//            closeButton.setOnClickListener {
//                val homeIntent = Intent(this@DetailActivity, HomeActivity::class.java)
//                startActivity(homeIntent
//                    .addFlags(
//                        Intent.FLAG_ACTIVITY_CLEAR_TOP or
//                                Intent.FLAG_ACTIVITY_CLEAR_TASK or
//                                Intent.FLAG_ACTIVITY_NEW_TASK
//                    )
//                )
//                finish()
//            }
        }

    }

    companion object {
        const val EXTRA_PREDICT = "extra_predict"
    }
}