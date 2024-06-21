package com.bangkitacademy.harvesthero.ui.add

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bangkitacademy.harvesthero.R
import com.bangkitacademy.harvesthero.data.local.entity.Plant
import com.bangkitacademy.harvesthero.databinding.ActivityAddBinding
import com.bangkitacademy.harvesthero.ui.ViewModelFactory
import com.bangkitacademy.harvesthero.ui.detail.DetailActivity
import com.google.android.material.chip.Chip

class AddActivity : AppCompatActivity() {

    private val viewModel by viewModels<AddViewModel> {
        ViewModelFactory.getInstance(application)
    }
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
                setAddDataAndAction()
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

    private fun setAddDataAndAction() {

        val plantName = binding.edNameAdd.editText?.text.toString()
        val plantType = "Arabidopsis thaliana"  // This should be selected from the UI
        val currentGrowth = getCurrentGrowthFromChipGroup()

        if (plantName.isEmpty() || plantType.isEmpty() || currentGrowth.isEmpty()) {
            Toast.makeText(this@AddActivity, "Please enter all required fields", Toast.LENGTH_SHORT).show()
            return
        }

        val newPlant = Plant(
            id = 0, // Room will auto-generate this value
            plantName = plantName,
            plantType = plantType,
            imageString = "image_url",  // Fetch this from API
            desc = "Description from API",  // Fetch this from API
            currentGrowth = currentGrowth,
            timeToGrowth = 90,  // Example value, fetch this from API if needed
            isWatered = false,  // Example value
            timeToWater = 24,  // Example value
            isFertilized = false,  // Example value
            timeToFertilize = 30,  // Example value
            isSick = false  // Example value
        )

        viewModel.insert(newPlant) { insertedId ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("PLANT_ID", insertedId.toInt())
            startActivity(intent)
            finish()
        }
    }

    private fun getCurrentGrowthFromChipGroup(): String {
        val checkedChipId = binding.chipGroupAdd.checkedChipId
        return if (checkedChipId != -1) {
            val chip = binding.chipGroupAdd.findViewById<Chip>(checkedChipId)
            chip.text.toString()
        } else {
            ""
        }
    }
}