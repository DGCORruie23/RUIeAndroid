package com.example.electrorui.networkApi.model

import com.google.gson.annotations.SerializedName



data class UpdateModel(
    @SerializedName("version") val versionUp: String,
    @SerializedName("msg") val mensajeUp : String,
    @SerializedName("info") val info : Boolean,
)
