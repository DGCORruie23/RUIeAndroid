package com.example.electrorui.ui

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.hardware.display.DisplayManager
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Display
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.getSystemService
import androidx.core.graphics.drawable.DrawableCompat
import com.example.electrorui.databinding.ActivityNombresBinding
import com.example.electrorui.databinding.ToastLayoutErrorBinding
import com.example.electrorui.ui.fragments.DatePickerFragment
import com.example.electrorui.ui.viewModel.Nombres_AVM
import com.example.electrorui.usecase.model.RegistroNombres
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.AndroidEntryPoint
import org.threeten.bp.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class NombresActivity : AppCompatActivity() {

    companion object{
        val EXTRA_NACIONALIDAD = "NombresActivity:nacionalidad"
    }


    private lateinit var binding: ActivityNombresBinding
    private val dataActivityViewM : Nombres_AVM by viewModels()
    private lateinit var icon : Drawable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNombresBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        AndroidThreeTen.init(this)

        val nacio  = emptyList<String>()

        icon = AppCompatResources.getDrawable(this, com.example.electrorui.R.drawable.ic_error_24)!!
        DrawableCompat.setTint(icon, resources.getColor(com.example.electrorui.R.color.rojo))
        icon.setBounds(0, 0, icon.intrinsicWidth, icon.intrinsicHeight)

        val nacionalidadPadre = intent.getStringExtra(EXTRA_NACIONALIDAD)
        binding.spinnerPAIS.setText(nacionalidadPadre)

        binding.checkHombre.isChecked = true

        var autocompleteArrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nacio)
        binding.spinnerPAIS.threshold = 1
        binding.spinnerPAIS.setAdapter(autocompleteArrayAdapter)

        dataActivityViewM.paises.observe(this){
            binding.spinnerPAIS.setAdapter(ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, it))
            autocompleteArrayAdapter.notifyDataSetChanged()
        }

        binding.checkHombre.setOnClickListener {
            binding.checkHombre.isChecked = true
            binding.checkMujer.isChecked = false

            binding.LLEmbarazado.visibility = View.GONE
            binding.checkEmbarazada.isChecked = false
        }

//        binding.ldFechaNacimiento.setMaxLocalDate(org.threeten.bp.LocalDate.now())
        binding.ldFechaNacimiento.setMaxLocalDate(
            org.threeten.bp.LocalDate.parse(
                LocalDate.now().toString(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
            )
        )
        binding.ldFechaNacimiento.setMinLocalDate(
            org.threeten.bp.LocalDate.parse(
                "01/01/1920",
                DateTimeFormatter.ofPattern("dd/MM/yyyy")
            )
        )
//        binding.ldFechaNacimiento.localDate = org.threeten.bp.LocalDate.now()
        binding.ldFechaNacimiento.setOnLocalDatePickListener {date ->
            val fecha = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            hideKeyboard()

        }

        binding.checkMujer.setOnClickListener {
            binding.checkHombre.isChecked = false
            binding.checkMujer.isChecked = true

            binding.LLEmbarazado.visibility = View.VISIBLE
        }

        binding.btnGuardar.setOnClickListener {

            val nacionalidad = binding.spinnerPAIS.text.toString()
            val iso3D = dataActivityViewM.iso3.value
            val nom = binding.etNombre.text.toString().uppercase()
            val apellidos = binding.etApellidos.text.toString().uppercase()
            val noIdentidad = binding.etNoIdentidad.text.toString()

            val sexo : Boolean = binding.checkHombre.isChecked

            val paises = dataActivityViewM.paises.value
            val indexNacionalidad = paises?.indexOf(nacionalidad)

            if (nom.isNullOrEmpty()){
                binding.etNombre.setError("LLENAR PARA CONTINUAR", icon)
                binding.etNombre.requestFocus()
            } else{
                binding.etNombre.error = null
                if (apellidos.isNullOrEmpty()){
                    binding.etApellidos.setError("LLENAR PARA CONTINUAR", icon)
                    binding.etApellidos.requestFocus()
                } else {
                    binding.etApellidos.error = null
                    if (binding.ldFechaNacimiento.localDate == null){
                        binding.fechaNacIcon.visibility = View.VISIBLE
                        showToastError("LLENAR PARA CONTINUAR", Toast.LENGTH_LONG)
                    } else {
                        binding.fechaNacIcon.visibility = View.GONE

                        val fechaNacimiento = binding.ldFechaNacimiento.localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        val fechaNacimientoDate = SimpleDateFormat("dd/MM/yyyy").parse(fechaNacimiento)
                        val fechaActual = Date(System.currentTimeMillis())
                        val diferencia = fechaActual.time - fechaNacimientoDate?.time!!
                        val edad : Float = diferencia.toFloat() / (31536000000)
                        val embarazada = binding.checkEmbarazada.isChecked

                        val datosRetorno = RegistroNombres(
                            0,
                            nacionalidad,
                            iso3D!![indexNacionalidad!!],
                            nom,
                            apellidos,
                            noIdentidad,
                            fechaNacimiento,
                            edad > 18,
                            sexo,
                            embarazada
                        )

                        dataActivityViewM.saveToDB(datosRetorno)
                        Toast.makeText(applicationContext, "Guardando información", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }

            }
        }

        dataActivityViewM.onCreate()

    }

    fun showToastError(texto : String, duracion : Int){

        val displaySize = DisplayMetrics()
        getSystemService<DisplayManager>()?.getDisplay(Display.DEFAULT_DISPLAY)?.getMetrics(displaySize)

        val bindingToast = ToastLayoutErrorBinding.inflate(layoutInflater)
        bindingToast.textview.setText(texto)
        val toast = Toast(this)
        toast.view = bindingToast.root
        toast.duration = duracion
        toast.setGravity(Gravity.BOTTOM, 0, (displaySize.heightPixels / 2) - 20)
//        displaySize.heightPixels
        toast.show()

    }

//    private fun ShowDatePickerDialog() {
//        val datePickerDialog = DatePickerFragment { date -> onDateSelected(date)}
//        datePickerDialog.show(supportFragmentManager, "date")
//    }
//
//    private fun onDateSelected(date: String) {
//        binding.etFechaNacimiento.setText(date)
//    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}