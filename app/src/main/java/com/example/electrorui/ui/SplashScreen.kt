package com.example.electrorui.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.electrorui.R
import com.example.electrorui.db.PrefManager
import com.example.electrorui.databinding.ActivitySplashScreenBinding
import com.example.electrorui.ui.viewModel.SplashScreen_AVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {

    companion object{
        const val MY_CHANNEL_ID = "channelRUI"
    }

    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var prefManager: PrefManager
    private val dataActivityViewM : SplashScreen_AVM by viewModels()

    var contar = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        createChannel()
        createNotificationChannel(this)

        init()

//        val cm = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val activeNetwork : NetworkInfo? = cm.activeNetworkInfo
//        val isConnected : Boolean = activeNetwork?.isConnectedOrConnecting == true
//
//        prefManager.setConnection(isConnected)
//        prefManager.setvistasPopUpInternet(false)
//
//        dataActivityViewM.conectadoInternet.value = isConnected
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    1001
                )
            }
        }

        dataActivityViewM.verifyInter()

        dataActivityViewM.conectadoInternet.observe(this){
            prefManager.setConnection(it)
            prefManager.setvistasPopUpInternet(false)

            dataActivityViewM.onCreate()
        }

        dataActivityViewM.porcentProgress.observe(this){
            binding.progressBar.progress = it
            binding.tvProcent.setText("${it}%")
        }

        dataActivityViewM.mensajeNotif.observe(this){
            if (!it.isNullOrEmpty()){
//                mostrarNotificacion(it)
                mostrarNotificacion(this, it)
            }
        }

        dataActivityViewM.nombreUser.observe(this){
            prefManager.setUsername(it)
        }

        dataActivityViewM.statusMessage.observe(this){
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

        dataActivityViewM.continuar.observe(this){
            if (it == 1){
                val intentMain = Intent(this, MainActivity::class.java)
                startActivity(intentMain)
                finish()
            } else if( it == 2){
                prefManager.removeData()
                val loginIntent = Intent(this@SplashScreen, StartActivity::class.java)
                startActivity(loginIntent)
                finish()
            } else {

            }
        }

        dataActivityViewM.oficinaRepresentacion.observe(this){
            prefManager.setState(it)
        }



    }

    private fun init(){
        prefManager = PrefManager(this)
    }

//    fun createChannel(){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
//                != PackageManager.PERMISSION_GRANTED) {
//
//                ActivityCompat.requestPermissions(
//                    this,
//                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
//                    1001
//                )
//            }
//        }
//
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            val channel = NotificationChannel(
//                MY_CHANNEL_ID,
//                "MyRUISChannel",
//                NotificationManager.IMPORTANCE_DEFAULT
//            ).apply {
//                description = "Informacion envio"
//            }
//
//            val notificationManager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//    }
//
//    fun createSimpleNotif(info : String){
//
//        var nBuilder = NotificationCompat
//            .Builder(this, MY_CHANNEL_ID)
//            .setSmallIcon(R.drawable.ic_rui)
//            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_rui))
//            .setContentTitle("Datos del RUI")
//            .setContentText("Consulta el envio de datos")
//            .setStyle(
//                NotificationCompat.BigTextStyle().bigText(info)
//            )
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//
//        with(NotificationManagerCompat.from(this)){
//            notify(1, nBuilder.build())
//        }
//    }

    fun createNotificationChannel(context: Context) {
        val channel = NotificationChannel(
            "mi_canal_id", // ID
            "Mi Canal",     // Nombre visible
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "Descripción del canal"
        }

        val manager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }

    fun mostrarNotificacion(context: Context, info : String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Log.e("Notificacion", "Permiso de notificación no concedido")
                return
            }
        }

        val builder = NotificationCompat.Builder(context, "mi_canal_id")
            .setSmallIcon(R.drawable.ic_rui)
            .setContentTitle("Datos del RUI")
            .setContentText("Consulta el envio de datos")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        NotificationManagerCompat.from(context).notify(1, builder.build())
    }
}