package com.periodization.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val txtResultado = findViewById<TextView>(R.id.txtResultado)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        val nombre = intent.getStringExtra("nombre") ?: "Usuario"

        txtResultado.text = "Hola, $nombre"

        btnVolver.setOnClickListener {
            finish()
        }
    }
}