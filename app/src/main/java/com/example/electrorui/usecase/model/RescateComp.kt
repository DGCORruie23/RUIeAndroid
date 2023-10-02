package com.example.electrorui.usecase.model

import android.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.electrorui.db.entityModel.RescateCompEntity
import com.example.electrorui.db.entityModel.RescateEntity
import com.example.electrorui.networkApi.model.RescateCompModel
import java.text.SimpleDateFormat
import java.util.Date


data class RescateComp(
    val oficinaRepre: String,
    val fecha: String,
    val hora: String,

    val aeropuerto : Boolean,

    val carretero : Boolean,
    val tipoVehic : String,
    val lineaAutobus : String,
    val numeroEcono : String,
    val placas : String,

    val vehiculoAseg : Boolean,

    val casaSeguridad : Boolean,

    val centralAutobus : Boolean,

    val ferrocarril : Boolean,
    val empresa: String,

    val hotel : Boolean,
    val nombreHotel : String,

    val puestosADispo : Boolean,
    val juezCalif : Boolean,
    val reclusorio : Boolean,
    val policiaFede : Boolean,
    val dif : Boolean,
    val policiaEsta : Boolean,
    val policiaMuni : Boolean,
    val guardiaNaci : Boolean,
    val fiscalia : Boolean,
    val otrasAuto : Boolean,

    val voluntarios : Boolean,

    val otro : Boolean,

    val presuntosDelincuentes : Boolean,
    val numPresuntosDelincuentes : Int,
    val municipio : String,

    val puntoEstra : String,

    val nacionalidad : String,
    val iso3 : String,
    val nombre : String,
    val apellidos : String,
    val noIdentidad : String,
    val parentesco : String,
    val fechaNacimiento : String,
    val sexo : Boolean,
    val numFamilia : Int,
    val edad : Int,
    ){
    constructor(rescate : Rescate, rescateF : RegistroFamilias) : this(
        rescate.oficinaRepre,
        rescate.fecha,
        rescate.hora,
        rescate.aeropuerto,
        rescate.carretero,
        rescate.tipoVehic,
        rescate.lineaAutobus,
        rescate.numeroEcono,
        rescate.placas,
        rescate.vehiculoAseg,
        rescate.casaSeguridad,
        rescate.centralAutobus,
        rescate.ferrocarril,
        rescate.empresa,
        rescate.hotel,
        rescate.nombreHotel,
        rescate.puestosADispo,
        rescate.juezCalif,
        rescate.reclusorio,
        rescate.policiaFede,
        rescate.dif,
        rescate.policiaEsta,
        rescate.policiaMuni,
        rescate.guardiaNaci,
        rescate.fiscalia,
        rescate.otrasAuto,
        rescate.voluntarios,
        rescate.otro,
        rescate.presuntosDelincuentes,
        rescate.numPresuntosDelincuentes,
        rescate.municipio,
        rescate.puntoEstra,

        rescateF.nacionalidad,
        rescateF.iso3,
        rescateF.nombre,
        rescateF.apellidos,
        rescateF.noIdentidad,
        rescateF.parentesco,
        rescateF.fechaNacimiento,
        rescateF.sexo,
        rescateF.numFamilia,
        rescateF.getEdad(),
    )

    constructor(rescate : Rescate, rescateF : RegistroNombres) : this(
        rescate.oficinaRepre,
        rescate.fecha,
        rescate.hora,
        rescate.aeropuerto,
        rescate.carretero,
        rescate.tipoVehic,
        rescate.lineaAutobus,
        rescate.numeroEcono,
        rescate.placas,
        rescate.vehiculoAseg,
        rescate.casaSeguridad,
        rescate.centralAutobus,
        rescate.ferrocarril,
        rescate.empresa,
        rescate.hotel,
        rescate.nombreHotel,
        rescate.puestosADispo,
        rescate.juezCalif,
        rescate.reclusorio,
        rescate.policiaFede,
        rescate.dif,
        rescate.policiaEsta,
        rescate.policiaMuni,
        rescate.guardiaNaci,
        rescate.fiscalia,
        rescate.otrasAuto,
        rescate.voluntarios,
        rescate.otro,
        rescate.presuntosDelincuentes,
        rescate.numPresuntosDelincuentes,
        rescate.municipio,
        rescate.puntoEstra,

        rescateF.nacionalidad,
        rescateF.iso3,
        rescateF.nombre,
        rescateF.apellidos,
        rescateF.noIdentidad,
        "",
        rescateF.fechaNacimiento,
        rescateF.sexo,
        0,
        rescateF.getEdad(),
    )
}


fun RescateCompEntity.toUC() = RescateComp(
    oficinaRepre,
    fecha,
    hora,
    aeropuerto,
    carretero,
    tipoVehic,
    lineaAutobus,
    numeroEcono,
    placas,
    vehiculoAseg,
    casaSeguridad,
    centralAutobus,
    ferrocarril,
    empresa,
    hotel,
    nombreHotel,
    puestosADispo,
    juezCalif,
    reclusorio,
    policiaFede,
    dif,
    policiaEsta,
    policiaMuni,
    guardiaNaci,
    fiscalia,
    otrasAuto,
    voluntarios,
    otro,
    presuntosDelincuentes,
    numPresuntosDelincuentes,
    municipio,
    puntoEstra,
    nacionalidad,
    iso3,
    nombre,
    apellidos,
    noIdentidad,
    parentesco,
    fechaNacimiento,
    sexo,
    numFamilia,
    edad)

fun RescateCompModel.toUC() = RescateComp(
    oficinaRepre,
    fecha,
    hora,
    aeropuerto,
    carretero,
    tipoVehic,
    lineaAutobus,
    numeroEcono,
    placas,
    vehiculoAseg,
    casaSeguridad,
    centralAutobus,
    ferrocarril,
    empresa,
    hotel,
    nombreHotel,
    puestosADispo,
    juezCalif,
    reclusorio,
    policiaFede,
    dif,
    policiaEsta,
    policiaMuni,
    guardiaNaci,
    fiscalia,
    otrasAuto,
    voluntarios,
    otro,
    presuntosDelincuentes,
    numPresuntosDelincuentes,
    municipio,
    puntoEstra,
    nacionalidad,
    iso3,
    nombre,
    apellidos,
    noIdentidad,
    parentesco,
    fechaNacimiento,
    sexo,
    numFamilia,
    edad)

