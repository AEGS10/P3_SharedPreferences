package com.example.p3_sharedpreferences

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private val PREFS = "config"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences(PREFS, MODE_PRIVATE)
        val logueado = prefs.getBoolean("logueado", false)

        if (logueado) {
            irUltimaPantalla()
            return
        }

        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.btnLogin).setOnClickListener {
            prefs.edit().putBoolean("logueado", true).apply()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun irUltimaPantalla() {
        val prefs = getSharedPreferences(PREFS, MODE_PRIVATE)
        when (prefs.getString("ultimaPantalla", "main")) {
            "segunda" -> startActivity(Intent(this, SegundaActivity::class.java))
            "config" -> startActivity(Intent(this, AdvancedConfigActivity::class.java))
            else -> startActivity(Intent(this, MainActivity::class.java))
        }
        finish()
    }
}
