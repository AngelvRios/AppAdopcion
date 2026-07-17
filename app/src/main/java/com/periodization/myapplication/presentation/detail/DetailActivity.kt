package com.periodization.myapplication.presentation.detail

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.periodization.myapplication.R
import com.periodization.myapplication.databinding.ActivityDetailBinding
import com.periodization.myapplication.domain.model.Pet
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pet = getSerializable(intent, "pet", Pet::class.java)

        pet?.let { setupUi(it) } ?: run {
            Toast.makeText(this, "Error: Pet data not found", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun setupUi(pet: Pet) {
        binding.apply {
            tvNameDetail.text = pet.name
            tvLocationDetail.text = getString(R.string.ubicacion_label, pet.location ?: "N/A")
            tvAgeGender.text = "${pet.age} • ${pet.gender}"
            tvDescriptionDetail.text = pet.description ?: ""
            
            ivPetDetail.load(pet.imageUrl) {
                crossfade(true)
                placeholder(android.R.drawable.ic_menu_gallery)
                error(android.R.drawable.ic_menu_report_image)
            }

            btnAdopt.setOnClickListener {
                Toast.makeText(this@DetailActivity, getString(R.string.toast_contacto), Toast.LENGTH_SHORT).show()
            }

            btnBack.setOnClickListener {
                finish()
            }
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