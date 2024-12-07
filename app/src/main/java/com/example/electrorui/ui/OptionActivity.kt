package com.example.electrorui.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.electrorui.databinding.ActivityOptionBinding

class OptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonConteoRapido.setOnClickListener {
            startActivity(Intent(this,ConteoRActivity::class.java))
        }

        binding.buttonDisuaciones.setOnClickListener {
            startActivity(Intent(this,DisuadidosActivity::class.java))
        }

    }
}