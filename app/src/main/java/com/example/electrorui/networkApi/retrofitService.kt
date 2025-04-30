package com.example.electrorui.networkApi

import android.util.Log
import com.example.electrorui.di.NetworkModule
import com.example.electrorui.networkApi.model.ConteoRapidoCompModel
import com.example.electrorui.networkApi.model.DisuadidosModel
import com.example.electrorui.networkApi.model.FuerzaModel
import com.example.electrorui.networkApi.model.LoginModel
import com.example.electrorui.networkApi.model.MunicipiosModel
import com.example.electrorui.networkApi.model.PaisModel
import com.example.electrorui.networkApi.model.PuntosInterModel
import com.example.electrorui.networkApi.model.RescateCompModel
import com.example.electrorui.networkApi.model.UpdateModel
import com.example.electrorui.usecase.model.RespuestaA
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject
import javax.inject.Named

class retrofitService @Inject constructor(
//    private val retrofit: ApisServices
    @Named("primary") private val primaryApi: ApisServices,
    @Named("secondary") private val secondaryApi: ApisServices
) {
//    suspend fun verifyUser(loginData: LoginModel): LoginModel {
//        return withContext(Dispatchers.IO){
//            val response = primaryApi.verificarUsuario(loginData)
//            response
//
//        }
//    }
    suspend fun verifyUser(loginData: LoginModel): LoginModel = withContext(Dispatchers.IO) {
        try {
            val resp2 = secondaryApi.verificarUsuario(loginData)
            resp2.let {
                Log.e("API", "Éxito primaria: $it")
                return@withContext it
            }
        } catch (e: Exception) { Log.e("API", "Error en verifyUser: ${e.message}") }

        try {
            val resp1 = primaryApi.verificarUsuario(loginData)
            resp1.let {
                Log.e("API", "Éxito secundaria: $it")
                return@withContext it
            }
        } catch (e: Exception) {
            Log.e("API", "Error en verifyUser: ${e.message}")
        }

        Log.i("API", "Devolviendo objeto vacío verifyUser")
        return@withContext LoginModel("", "", "", "", "", "")
    }

    suspend fun verifyInternet(): Boolean = withContext(Dispatchers.IO) {
        var code: Int = 0
        try {
            val url = URL(NetworkModule.url1)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.connectTimeout = 3 * 1000
            connection.connect()
            code = connection.responseCode
            if(code == 200) return@withContext true

        } catch (e: Exception) {
            Log.e("API", "Error en verificar Internet 1: ${e.message}")
        }

        try {NetworkModule.url2
            val url = URL(NetworkModule.url2)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            // esperar 1 segundos para verificar
            connection.connectTimeout = 3 * 1000
            connection.connect()
            code = connection.responseCode
            if(code == 200) return@withContext true

        } catch (e: Exception) {
            Log.e("API", "Error en verificar Internet 2: ${e.message}")
        }

        return@withContext false
    }

    suspend fun getPaises(): List<PaisModel> = withContext(Dispatchers.IO){
        try {
            val resp2 = secondaryApi.getAllPaises()
            resp2.let {
                Log.e("API", "Éxito primaria: $it")
                return@withContext it
            }
        } catch (e: Exception) { Log.e("API", "Error en getPaises: ${e.message}") }

        try {
            val resp1 = primaryApi.getAllPaises()
            resp1.let {
                Log.e("API", "Éxito secundaria: $it")
                return@withContext it
            }
        } catch (e: Exception) {
            Log.e("API", "Error en getPaises: ${e.message}")
        }

        Log.i("API", "Devolviendo objeto vacío getPaises")
        return@withContext emptyList()
//        val response = primaryApi.getAllPaises()
//        response
    }

    suspend fun getFuerza(): List<FuerzaModel> = withContext(Dispatchers.IO){
        try {
            val resp2 = secondaryApi.getAllFuerza()
            resp2.let {
                Log.e("API", "Éxito primaria: $it")
                return@withContext it
            }
        } catch (e: Exception) { Log.e("API", "Error en getFuerza: ${e.message}") }

        try {
            val resp1 = primaryApi.getAllFuerza()
            resp1.let {
                Log.e("API", "Éxito secundaria: $it")
                return@withContext it
            }
        } catch (e: Exception) {
            Log.e("API", "Error en getFuerza: ${e.message}")
        }

        Log.i("API", "Devolviendo objeto vacío getFuerza")
        return@withContext emptyList()
//        val response = primaryApi.getAllFuerza()
//        response
    }

    suspend fun getMunicipios(): List<MunicipiosModel> = withContext(Dispatchers.IO){
        try {
            val resp2 = secondaryApi.getAllMunicipios()
            resp2.let {
                Log.e("API", "Éxito primaria: $it")
                return@withContext it
            }
        } catch (e: Exception) { Log.e("API", "Error en getMunicipios: ${e.message}") }

        try {
            val resp1 = primaryApi.getAllMunicipios()
            resp1.let {
                Log.e("API", "Éxito secundaria: $it")
                return@withContext it
            }
        } catch (e: Exception) {
            Log.e("API", "Error en getMunicipios: ${e.message}")
        }

        Log.i("API", "Devolviendo objeto vacío getMunicipios")
        return@withContext emptyList()
//        val response = primaryApi.getAllMunicipios()
//        response
    }

    suspend fun getPuntosInter(): List<PuntosInterModel> = withContext(Dispatchers.IO){
        try {
            val resp2 = secondaryApi.getAllPuntosInter()
            resp2.let {
                Log.e("API", "Éxito primaria: $it")
                return@withContext it
            }
        } catch (e: Exception) { Log.e("API", "Error en getAllPuntosInter: ${e.message}") }

        try {
            val resp1 = primaryApi.getAllPuntosInter()
            resp1.let {
                Log.e("API", "Éxito secundaria: $it")
                return@withContext it
            }
        } catch (e: Exception) {
            Log.e("API", "Error en getAllPuntosInter: ${e.message}")
        }

        Log.i("API", "Devolviendo objeto vacío getAllPuntosInter")
        return@withContext emptyList()
//        val response = primaryApi.getAllPuntosInter()
//        response
    }

    suspend fun getVersion(): UpdateModel = withContext(Dispatchers.IO){
        try {
            val resp2 = secondaryApi.getVersionUpdate()
            resp2.let {
                Log.e("API", "Éxito primaria: $it")
                return@withContext it
            }
        } catch (e: Exception) { Log.e("API", "Error en getVersionUpdate: ${e.message}") }

        try {
            val resp1 = primaryApi.getVersionUpdate()
            resp1.let {
                Log.e("API", "Éxito secundaria: $it")
                return@withContext it
            }
        } catch (e: Exception) {
            Log.e("API", "Error en getVersionUpdate: ${e.message}")
        }

        Log.i("API", "Devolviendo objeto vacío getVersionUpdate")
        return@withContext UpdateModel("", "", false)
//        val response = primaryApi.getVersionUpdate()
//        response
    }

    suspend fun setRescates(registros : List<RescateCompModel>): RespuestaA = withContext(Dispatchers.IO){
        try {
            val resp2 = secondaryApi.insertRescates(registros)
            resp2.let {
                Log.e("API", "Éxito primaria: $it")
                return@withContext it
            }
        } catch (e: Exception) { Log.e("API", "Error en insertRescates: ${e.message}") }

        try {
            val resp1 = primaryApi.insertRescates(registros)
            resp1.let {
                Log.e("API", "Éxito secundaria: $it")
                return@withContext it
            }
        } catch (e: Exception) {
            Log.e("API", "Error en getAllPuntosInter: ${e.message}")
        }

        Log.i("API", "Devolviendo objeto vacío insertRescates")
        return@withContext RespuestaA("")
//        val response = primaryApi.insertRescates(registros)
//        response
    }

    suspend fun setConteos(registros : List<ConteoRapidoCompModel>): RespuestaA = withContext(Dispatchers.IO){
        try {
            val resp2 = secondaryApi.insertConteo(registros)
            resp2.let {
                Log.e("API", "Éxito primaria: $it")
                return@withContext it
            }
        } catch (e: Exception) { Log.e("API", "Error en insertConteo: ${e.message}") }

        try {
            val resp1 = primaryApi.insertConteo(registros)
            resp1.let {
                Log.e("API", "Éxito secundaria: $it")
                return@withContext it
            }
        } catch (e: Exception) {
            Log.e("API", "Error en insertConteo: ${e.message}")
        }

        Log.i("API", "Devolviendo objeto vacío insertConteo")
        return@withContext RespuestaA("")
//       val response = primaryApi.insertConteo(registros)
//        response
    }

    suspend fun setDisuadidos(registros : List<DisuadidosModel>): RespuestaA = withContext(Dispatchers.IO){
        try {
            val resp2 = secondaryApi.insertDisuadidos(registros)
            resp2.let {
                Log.e("API", "Éxito primaria: $it")
                return@withContext it
            }
        } catch (e: Exception) { Log.e("API", "Error en insertDisuadidos: ${e.message}") }

        try {
            val resp1 = primaryApi.insertDisuadidos(registros)
            resp1.let {
                Log.e("API", "Éxito secundaria: $it")
                return@withContext it
            }
        } catch (e: Exception) {
            Log.e("API", "Error en insertDisuadidos: ${e.message}")
        }

        Log.i("API", "Devolviendo objeto vacío insertDisuadidos")
        return@withContext RespuestaA("")
//        val response = primaryApi.insertDisuadidos(registros)
//        response
    }

}