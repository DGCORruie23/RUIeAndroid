package com.example.electrorui.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.electrorui.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCerrarSesion.setOnClickListener {
            Toast.makeText(this, "Función por implementar", Toast.LENGTH_SHORT).show()
        }

    }
}