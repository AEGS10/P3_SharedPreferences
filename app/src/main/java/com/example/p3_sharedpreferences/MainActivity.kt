package com.example.p3_sharedpreferences

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    lateinit var etNombre: EditText
    lateinit var swModoOscuro: Switch
    lateinit var btnGuardar: Button
    lateinit var btnCargar: Button
    lateinit var btnSegunda: Button
    lateinit var btnConfig: Button
    lateinit var tvResultado: TextView

    lateinit var preferencias: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {


        preferencias = getSharedPreferences("datos", MODE_PRIVATE)
        val modoOscuroActivo = preferencias.getBoolean("oscuro", false)

        if (modoOscuroActivo) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNombre = findViewById(R.id.etNombre)
        swModoOscuro = findViewById(R.id.swModoOscuro)
        btnGuardar = findViewById(R.id.btnGuardar)
        btnCargar = findViewById(R.id.btnCargar)
        btnSegunda = findViewById(R.id.btnSegunda)
        btnConfig = findViewById(R.id.btnConfig)
        tvResultado = findViewById(R.id.tvResultado)

        swModoOscuro.isChecked = modoOscuroActivo


        swModoOscuro.setOnCheckedChangeListener { _, isChecked ->

            val editor = preferencias.edit()
            editor.putBoolean("oscuro", isChecked)
            editor.apply()

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

            recreate()
        }

        btnGuardar.setOnClickListener {
            val editor = preferencias.edit()
            editor.putString("nombre", etNombre.text.toString())
            editor.putBoolean("oscuro", swModoOscuro.isChecked)
            editor.apply()

            Toast.makeText(this, "Preferencias guardadas", Toast.LENGTH_SHORT).show()
        }

        btnCargar.setOnClickListener {

            val nombre = preferencias.getString("nombre", "Sin nombre")
            val oscuro = preferencias.getBoolean("oscuro", false)

            etNombre.setText(nombre)
            swModoOscuro.isChecked = oscuro

            tvResultado.text = """
                Preferencias guardadas:
                
                Nombre: $nombre
                Modo oscuro: $oscuro
            """.trimIndent()
        }

        btnSegunda.setOnClickListener {
            startActivity(Intent(this, SegundaActivity::class.java))
        }

        btnConfig.setOnClickListener {
            startActivity(Intent(this, AdvancedConfigActivity::class.java))
        }
    }
}
