package com.example.electrorui.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.electrorui.databinding.ActivityDisuadidosBinding
import com.example.electrorui.ui.viewModel.Disuadidos_AVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DisuadidosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDisuadidosBinding
    private var isConnected : Boolean = false

    private val dataActivityViewM : Disuadidos_AVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisuadidosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataActivityViewM.onCreate()

        dataActivityViewM.view_Agente.observe(this, binding.TVAgente::setText)
        dataActivityViewM.view_Fecha.observe(this, binding.TVFecha::setText)
        dataActivityViewM.view_Punto.observe(this, binding.tvPuntoRevision::setText)

        binding.btnEnviarDisuadidos.setOnClickListener {
            val NumDissuadidos = binding.ETNumberD.text.toString()

            dataActivityViewM.var_numDisuadidos.value = NumDissuadidos.toInt()

            dataActivityViewM.enviarDisuadidosAPI()

            Toast.makeText(this, "Se enviaron los datos a API", Toast.LENGTH_LONG).show()
        }

    }
}