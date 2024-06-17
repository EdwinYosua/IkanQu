package com.capstoneapp.ikanqu.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.capstoneapp.ikanqu.ViewModelFactory
import com.capstoneapp.ikanqu.databinding.ActivityLoginBinding
import com.capstoneapp.ikanqu.network.ApiResult
import com.capstoneapp.ikanqu.ui.home.HomeActivity
import com.capstoneapp.ikanqu.ui.main.MainActivity
import com.capstoneapp.ikanqu.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
    private val loginViewModel: LoginViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            tvSignUpLink.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }

            btnLogin.setOnClickListener {
                val email = fieldEmail.text?.trim().toString()
                val pass = fieldPassword.text?.trim().toString()

                if (email.isEmpty()) {
                    showToast("Field Email Kosong!")
                }
                if (pass.isEmpty()) {
                    showToast("Field Password Kosong!")
                }

                if (email.isNotEmpty() && pass.isNotEmpty()) {
                    loginViewModel.loginUser(email, pass)
                    loginViewModel.loginResult.observe(this@LoginActivity) { result ->
                        when (result) {
                            is ApiResult.ApiSuccess -> {
                                showToast("LOGIN BERHASIL")
                                val intentLogin =
                                    Intent(this@LoginActivity, HomeActivity::class.java)
                                startActivity(
                                    intentLogin.addFlags(
                                        Intent.FLAG_ACTIVITY_CLEAR_TOP or
                                                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                                                Intent.FLAG_ACTIVITY_NEW_TASK
                                    )
                                )
                                finish()
                            }

                            is ApiResult.ApiError -> {
                                showToast(result.error)
                            }

                            ApiResult.ApiLoading -> {
                                showToast("LOADING")
                            }
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