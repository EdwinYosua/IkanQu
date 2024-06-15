package com.capstoneapp.ikanqu.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.capstoneapp.ikanqu.ViewModelFactory
import com.capstoneapp.ikanqu.databinding.ActivityRegisterBinding
import com.capstoneapp.ikanqu.network.ApiResult
import com.capstoneapp.ikanqu.ui.main.MainActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
    private val registerViewModel: RegisterViewModel by viewModels { factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)


        binding.apply {
            btnSignup.setOnClickListener {
                val name = fieldName.text?.trim().toString()
                val email = fieldEmail.text?.trim().toString()
                val password = fieldPassword.text?.trim().toString()
                val confirmPass = fieldConfirmPassword.text?.trim().toString()

                if (name.isEmpty()) {
                    showToast("Field Nama Kosong!")
                }
                if (email.isEmpty()) {
                    showToast("Field Email Kosong!")
                }
                if (password.isEmpty()) {
                    showToast("Field Password Kosong!")
                }
                if (confirmPass.isEmpty()) {
                    showToast("Field Konfirmasi Password Kosong!")
                }
                if (password != confirmPass) {
                    showToast("Password Tidak Sama!")
                }

                if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPass.isNotEmpty()) {
                    registerViewModel.registerUser(
                        name = name,
                        email = email,
                        password = password
                    )
                    registerViewModel.registerResponse.observe(this@RegisterActivity) { result ->
                        when (result) {
                            is ApiResult.ApiSuccess -> {
                                showToast("BERHASIL")
                                val homeIntent =
                                    Intent(this@RegisterActivity, MainActivity::class.java)
                                startActivity(
                                    homeIntent.addFlags(
                                        Intent.FLAG_ACTIVITY_CLEAR_TOP or
                                                Intent.FLAG_ACTIVITY_NEW_TASK or
                                                Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    )
                                )
                                finish()
                            }

                            is ApiResult.ApiError -> showToast(result.error)
                            ApiResult.ApiLoading -> showToast("LOADING BRAY")
                        }
                    }
                }
            }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}