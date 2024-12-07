package com.example.electrorui.usecase.model

import androidx.room.ColumnInfo
import com.example.electrorui.db.entityModel.DisuadidosEntity
import com.example.electrorui.db.entityModel.MensajeEntity


data class Disuadidos(
    var oficinaRepre: String,
    var fecha: String,
    var hora: String,

    var nombreAgente : String,
    var tipoPR : String = "",
    var nombrePR : String = "",
    var numDisuadidos : Int = 0,
){
    constructor() : this(
        "",
        "",
        "",
        "",
        "",
        0.toString(),
    )
}

fun DisuadidosEntity.toUC() = Disuadidos(oficinaRepre, fecha, hora, nombreAgente, tipoPR, nombrePR, numDisuadidos)