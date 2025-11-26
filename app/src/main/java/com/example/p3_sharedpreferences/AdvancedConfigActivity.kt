package com.example.p3_sharedpreferences

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AdvancedConfigActivity : AppCompatActivity() {

    private val PREFS = "config"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getSharedPreferences(PREFS, MODE_PRIVATE)
            .edit().putString("ultimaPantalla", "config").apply()

        setContentView(R.layout.activity_advanced)

        val swNotif = findViewById<Switch>(R.id.swNotif)
        val spFrecuencia = findViewById<Spinner>(R.id.spFrecuencia)
        val spIdioma = findViewById<Spinner>(R.id.spIdioma)

        val prefs = getSharedPreferences(PREFS, MODE_PRIVATE)

        swNotif.isChecked = prefs.getBoolean("notificaciones", false)

        findViewById<Button>(R.id.btnGuardarAvanzado).setOnClickListener {
            prefs.edit().apply {
                putBoolean("notificaciones", swNotif.isChecked)
                putString("frecuencia", spFrecuencia.selectedItem.toString())
                putString("idioma", spIdioma.selectedItem.toString())
                apply()
            }
            Toast.makeText(this, "Configuración guardada", Toast.LENGTH_SHORT).show()
        }
    }
}
