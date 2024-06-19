package com.bangkitacademy.harvesthero.ui.add

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bangkitacademy.harvesthero.R
import com.bangkitacademy.harvesthero.databinding.ActivityAddBinding
import com.bangkitacademy.harvesthero.ui.detail.DetailActivity
import com.google.android.material.chip.Chip

class AddActivity : AppCompatActivity() {

    private val viewModel: AddViewModel by viewModels()
    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setChipGroup()
        setAction()
    }

    private fun setAction() {
        binding.apply {

            // Go to PlantPedia activity to search plant type
            btnSearchTypeAdd.setOnClickListener{

            }

            // Get plant type additional data from API and add it to local database
            btnAdd.setOnClickListener{
                val intent = Intent(this@AddActivity, DetailActivity::class.java)

                setData()

                startActivity(intent)
                finish()
            }
        }
    }

    private fun setChipGroup() {

        val tags = listOf("Seed", "Germination", "Vegetative Growth", "Generative Growth")

        for (tag in tags) {
            val chip = Chip(this).apply {
                text = tag
                isCheckable = true
                isClickable = true
            }
            binding.chipGroupAdd.addView(chip)
        }
    }

    private fun setData() {

    }
}