package com.example.electrorui.db.entityModel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.electrorui.usecase.model.Disuadidos


@Entity(tableName = "disuadidos_table")
data class DisuadidosEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idDisuadidos") val idDisuadidos: Int = 0,

    @ColumnInfo(name = "oficinaRepre") val oficinaRepre: String,
    @ColumnInfo(name = "fecha") val fecha: String,
    @ColumnInfo(name = "hora") val hora: String,

    @ColumnInfo(name = "nombreAgente") val nombreAgente : String,
    @ColumnInfo(name = "tipoPuntoRevision") val tipoPR : String = "",
    @ColumnInfo(name = "nombrePuntoRevision") val nombrePR : String = "",
    @ColumnInfo(name = "numDisiadidos") val numDisuadidos : Int = 0,
)

fun Disuadidos.toDB() = DisuadidosEntity(
    oficinaRepre = oficinaRepre,
    fecha = fecha,
    hora = hora,
    nombreAgente = nombreAgente,
    tipoPR = tipoPR,
    nombrePR = nombrePR,
    numDisuadidos = numDisuadidos
)