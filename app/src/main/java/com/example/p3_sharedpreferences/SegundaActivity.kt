package com.example.p3_sharedpreferences

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SegundaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getSharedPreferences("config", MODE_PRIVATE)
            .edit().putString("ultimaPantalla", "segunda").apply()

        setContentView(R.layout.activity_segunda)
    }
}
