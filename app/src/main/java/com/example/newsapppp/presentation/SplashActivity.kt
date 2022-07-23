package com.example.newsapppp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import com.example.newsapppp.R
import com.example.newsapppp.databinding.ActivitySplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.Main).launch {
            delay(100)
//            findNavController().navigate(R.id.mainFragment)
            toMainActivity()
        }

      binding.btStart.setOnClickListener {
          toMainActivity()
      }
    }
    fun toMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

    }
}
