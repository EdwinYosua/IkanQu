package com.capstoneapp.ikanqu.ui.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.capstoneapp.ikanqu.R
import com.capstoneapp.ikanqu.ViewModelFactory
import com.capstoneapp.ikanqu.databinding.ActivityHomeBinding
import com.capstoneapp.ikanqu.network.ApiResult
import com.capstoneapp.ikanqu.ui.main.MainActivity
import com.capstoneapp.ikanqu.utils.uriToFile

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private var currentImg: Uri? = null
    private val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
    private val homeViewModel: HomeViewModel by viewModels { factory }

    private val reqPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            Toast.makeText(this, "Permission Request Granted", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Permission Request Denied", Toast.LENGTH_LONG).show()
        }
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImg = uri
            showImg()
        } else {
            Log.d("Photo Picker", "No Media Selected")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!allPermissionGranted()) {
            reqPermissionLauncher.launch(REQ_PERMISSION)
        }

        val userName = intent.getStringExtra(EXTRA_NAME) ?: "404"

        binding.apply {

            imageLabelTextView.text =
                getString(R.string.hallo_text, userName.split(" ").firstOrNull() ?: userName)

            galleryButton.setOnClickListener { startGallery() }
            analyzeButton.setOnClickListener {
                if (currentImg == null) {
                    showToast("No Media Selected")
                } else {
                    startUpload()
                }
            }

            logoutButton.setOnClickListener {
                homeViewModel.logout()
                startActivity(Intent(this@HomeActivity, MainActivity::class.java))
                finish()
            }

            homeViewModel.analyzeResponse.observe(this@HomeActivity) { response ->
                when (response) {
                    is ApiResult.ApiError -> showToast(response.error)
                    ApiResult.ApiLoading -> showToast("LOADING")
                    is ApiResult.ApiSuccess -> {
                        showToast(response.data.prediction.toString())
                    }
                }
            }
        }
    }

    private fun allPermissionGranted() =
        ContextCompat.checkSelfPermission(
            this, REQ_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED

    private fun showImg() {
        currentImg?.let {
            Log.d("Img Uri", "Show Image : $it")
            binding.previewImageView.setImageURI(it)
        }
    }

    private fun startUpload() {
        currentImg?.let { uri ->
            val imgFile = uriToFile(uri, this@HomeActivity)
            Log.d("Img", "SHow Img : ${imgFile.path}")
            homeViewModel.analyzeImg(imgFile)
        }
    }


    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val REQ_PERMISSION = Manifest.permission.CAMERA
        const val EXTRA_NAME = "extra_name"
    }
}