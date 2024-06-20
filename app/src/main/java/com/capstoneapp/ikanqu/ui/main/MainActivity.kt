package com.capstoneapp.ikanqu.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstoneapp.ikanqu.R
import com.capstoneapp.ikanqu.ViewModelFactory
import com.capstoneapp.ikanqu.databinding.ActivityMainBinding
import com.capstoneapp.ikanqu.ui.home.HomeActivity
import com.capstoneapp.ikanqu.ui.login.LoginActivity
import com.capstoneapp.ikanqu.ui.register.RegisterActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) //disable night mode in app

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val mainViewModel: MainViewModel by viewModels { factory }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {

            mainViewModel.getLoggedUserId().observe(this@MainActivity) { userId ->
                if (userId != null) {
                    mainViewModel.getLoggedUserName().observe(this@MainActivity) { userName ->
                        val homeIntent = Intent(this@MainActivity, HomeActivity::class.java)
                        homeIntent.putExtra(HomeActivity.EXTRA_NAME, userName)
                        startActivity(homeIntent)
                        finish()
                    }
                }
            }

            btnRegister.setOnClickListener {
                startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
            }
            btnLogin.setOnClickListener {
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            }
        }
    }
}