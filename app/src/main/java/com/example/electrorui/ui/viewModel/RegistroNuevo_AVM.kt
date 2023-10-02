package com.example.electrorui.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.electrorui.usecase.DelAllRegistrosUC
import com.example.electrorui.usecase.GetAllPaisesUC
import com.example.electrorui.usecase.GetAllRegistrosConteoUC
import com.example.electrorui.usecase.SetEntryRegistrosUC
import com.example.electrorui.usecase.model.RegistroNacionalidad
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistroNuevo_AVM @Inject constructor(
    private val setEntryRegistrosUC: SetEntryRegistrosUC,
    private val getAllPaisesUC: GetAllPaisesUC,

) : ViewModel(){

    val paises by lazy { MutableLiveData<List<String>>() }
    val iso3 by lazy { MutableLiveData<List<String>>() }

    fun onCreate(){
        viewModelScope.launch {
            val paisesDB = getAllPaisesUC()
            paises.value = paisesDB.map { x -> x.pais }
            iso3.value = paisesDB.map { x -> x.iso3 }
        }
    }

    fun saveToDB(registroNacionalidad: RegistroNacionalidad){
        viewModelScope.launch {
            setEntryRegistrosUC(
                listOf(registroNacionalidad)
            )
        }
    }

}