package com.example.electrorui.networkApi.model

import com.example.electrorui.usecase.model.Disuadidos
import com.google.gson.annotations.SerializedName

data class DisuadidosModel(
    @SerializedName("oficinaRepre") val oficinaRepre: String,
    @SerializedName("fecha") val fecha: String,
    @SerializedName("hora") val hora: String,

    @SerializedName("nombreAgente") val nombreAgente: String,
    @SerializedName("tipoPuntoRevision") val tipoPR: String,
    @SerializedName("nombrePuntoRevision") val nombrePR: String,
    @SerializedName("numDisiadidos") val numeroDisuadidos : Int,
)

fun Disuadidos.toAPI() = DisuadidosModel(
    oficinaRepre,
    fecha,
    hora,
    nombreAgente,
    tipoPR,
    nombrePR,
    numDisuadidos
   )
