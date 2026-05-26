package com.periodization.myapplication

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import java.io.Serializable

class ResultadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val mascota = getSerializable(intent, "mascota", Mascota::class.java)

        val ivDetalle = findViewById<ImageView>(R.id.ivDetalle)
        val tvNombreDetalle = findViewById<TextView>(R.id.tvNombreDetalle)
        val tvUbicacionDetalle = findViewById<TextView>(R.id.tvUbicacionDetalle)
        val tvEdadDetalle = findViewById<TextView>(R.id.tvEdadDetalle)
        val tvDescripcionDetalle = findViewById<TextView>(R.id.tvDescripcionDetalle)
        val btnAdoptar = findViewById<Button>(R.id.btnAdoptar)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        mascota?.let {
            tvNombreDetalle.text = it.nombre
            tvUbicacionDetalle.text = getString(R.string.ubicacion_label, it.ubicacion)
            tvEdadDetalle.text = getString(R.string.edad_label, it.edad)
            tvDescripcionDetalle.text = it.descripcion
            
            ivDetalle.load(it.imageUrl) {
                crossfade(true)
                placeholder(android.R.drawable.ic_menu_gallery)
                error(android.R.drawable.ic_menu_report_image)
            }
        }

        btnAdoptar.setOnClickListener {
            Toast.makeText(this, getString(R.string.toast_contacto), Toast.LENGTH_SHORT).show()
        }

        btnVolver.setOnClickListener {
            finish()
        }
    }

    private fun <T : Serializable> getSerializable(intent: android.content.Intent, key: String, clazz: Class<T>): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(key, clazz)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra(key) as? T
        }
    }
}