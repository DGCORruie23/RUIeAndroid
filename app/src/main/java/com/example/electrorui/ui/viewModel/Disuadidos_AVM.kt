package com.example.electrorui.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.electrorui.usecase.GetAllRescatesDB
import com.example.electrorui.usecase.SetDatosDisuadidosAPI
import com.example.electrorui.usecase.SetMensajeDB
import com.example.electrorui.usecase.model.Disuadidos
import com.example.electrorui.usecase.model.Rescate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import java.text.SimpleDateFormat
import java.util.Locale

@HiltViewModel
class Disuadidos_AVM @Inject constructor(
    private val setDatosDisuadidosAPI: SetDatosDisuadidosAPI,
    private val setMensajeDB: SetMensajeDB,
    private val getAllRescatesDB: GetAllRescatesDB,
) : ViewModel(){

    val view_Agente by lazy { MutableLiveData<String>() }
    val view_Fecha by lazy { MutableLiveData<String>() }
    val view_Punto by lazy { MutableLiveData<String>() }
    val var_infoRescate by lazy { MutableLiveData<Rescate>() }
    val var_numDisuadidos by lazy { MutableLiveData<Int>() }

    fun onCreate(){
        viewModelScope.launch {
            var_infoRescate.value = getAllRescatesDB().last()
            view_Agente.value = "Agente: ${var_infoRescate.value!!.nombreAgente}"
            view_Fecha.value = "Fecha: ${var_infoRescate.value!!.fecha}"
            view_Punto.value = "Punto de Rescate: ${var_infoRescate.value!!.puntoEstra}"
        }
    }

    fun enviarDisuadidosAPI(){
        viewModelScope.launch {
            var rescatesD = Disuadidos()
            rescatesD.oficinaRepre = var_infoRescate.value!!.oficinaRepre
            rescatesD.fecha = formatDate(var_infoRescate.value!!.fecha)
            rescatesD.hora = var_infoRescate.value!!.hora

            rescatesD.nombreAgente = var_infoRescate.value!!.nombreAgente
            rescatesD.nombrePR = var_infoRescate.value!!.puntoEstra

            if (var_infoRescate.value!!.aeropuerto) rescatesD.tipoPR = "aeropuerto"
            else if (var_infoRescate.value!!.carretero) rescatesD.tipoPR = "carretero"
            else if (var_infoRescate.value!!.ferrocarril) rescatesD.tipoPR = "ferroviario"
            else if (var_infoRescate.value!!.centralAutobus) rescatesD.tipoPR = "central de autobus"
            else if (var_infoRescate.value!!.voluntarios) rescatesD.tipoPR = "voluntarios"
            else if (var_infoRescate.value!!.puestosADispo) rescatesD.tipoPR = "puestos a disposicion"
            else if (var_infoRescate.value!!.hotel) rescatesD.tipoPR = "Visitas de verificacion"
            else if (var_infoRescate.value!!.casaSeguridad) rescatesD.tipoPR = "disuadidos"
            else rescatesD.tipoPR = ""

            rescatesD.numDisuadidos = var_numDisuadidos.value!!

            val mensajeR = setDatosDisuadidosAPI(listOf(rescatesD))

            Log.e("RESPUESTA API disuadidos", mensajeR)
        }
    }

    fun formatDate(inputDate: String): String {
        // Define el formato de entrada (dd-MM-yy)
        val inputFormat = SimpleDateFormat("dd-MM-yy", Locale.getDefault())
        // Define el formato de salida (yyyy-MM-dd)
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        // Parsear la fecha del formato de entrada
        val date = inputFormat.parse(inputDate)

        // Convertir al formato de salida
        return outputFormat.format(date!!)
    }

}

//@HiltViewModel
//class ConteoR_AVM @Inject constructor(
//    private val getAllRegistrosConteoUC: GetAllRegistrosConteoUC,
//    private val delAllRegistrosUC: DelAllRegistrosUC,
//    private val getAllRescatesDB: GetAllRescatesDB,
//    private val setConteoRapidoCompletoDB: SetConteoRapidoCompletoDB,
//    private val setConteoRapidoCompletoAPI: SetConteoRapidoCompletoAPI,
//
//    private val getInfoMasivoConteoRap: GetInfoMasivoConteoRap,
//    private val delConteoRByIdUC: DelConteoRByIdUC,
//) : ViewModel(){