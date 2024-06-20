package com.capstoneapp.ikanqu.ui.detail

import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.capstoneapp.ikanqu.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prediction = intent.getStringExtra(EXTRA_PREDICT) ?: "404"
        val img = Uri.parse(intent.getStringExtra(EXTRA_IMG))

        binding.apply {

            titleTextView.text = prediction
            previewImageView.setImageURI(img)

        }

    }

    companion object {
        const val EXTRA_PREDICT = "extra_predict"
        const val EXTRA_IMG = "extra_img"
    }
}