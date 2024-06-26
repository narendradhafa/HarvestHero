package com.bangkitacademy.harvesthero.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bangkitacademy.harvesthero.R
import com.bangkitacademy.harvesthero.data.local.entity.Plant
import com.bangkitacademy.harvesthero.databinding.ActivityDetailBinding
import com.bangkitacademy.harvesthero.ui.ViewModelFactory
import com.bangkitacademy.harvesthero.ui.add.AddActivity
import com.bangkitacademy.harvesthero.ui.checkgrowth.CheckGrowthActivity
import com.bangkitacademy.harvesthero.ui.main.MainActivity
import com.bangkitacademy.harvesthero.ui.myplants.MyPlantsFragment

class DetailActivity : AppCompatActivity() {

    private val viewModel by viewModels<DetailViewModel> {
        ViewModelFactory.getInstance(application)
    }
    private lateinit var binding: ActivityDetailBinding
    private var currentPlant: Plant? = null
    private var plantId: Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        plantId = intent.getIntExtra("PLANT_ID", -1)
        if (plantId != -1) {
            viewModel.get(plantId).observe(this) { plant ->
                plant?.let {
                    currentPlant = it
                    setView(it)
                }
            }
        }

        setAction()
        setupAction()
    }

    private fun setupAction() {
        binding.apply {
            btnBackDetail.setOnClickListener {
                val intent = Intent(this@DetailActivity, MyPlantsFragment::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        }
    }

    private fun setView(plant: Plant) {
        binding.apply {
            tvPlantNameDetail.text = plant.plantName.toString()
            tvPlantTypeDetail.text = plant.plantType
            tvGrowthPhaseDetail.text = plant.currentGrowth
            tvHealthTimeDetail.text = if (plant.isSick == true) {
                getString(R.string.sick)
            } else getString(R.string.healthy)
            tvWaterTimeDetail.text = if (plant.isWatered == true) {
                getString(R.string.watered)
            } else getString(R.string.not_yet)
            tvFertilizeTimeDetail.text = if (plant.isFertilized == true) {
                getString(R.string.fertilized)
            } else getString(R.string.not_yet
            )

            btnWaterDetail.visibility = if (plant.isWatered == true) View.GONE else View.VISIBLE
            btnFertilizeDetail.visibility = if (plant.isFertilized == true) View.GONE else View.VISIBLE
        }
    }

    private fun setAction() {
        binding.apply {

            btnWaterDetail.setOnClickListener {
                currentPlant?.let { viewModel.waterPlant(it.id) }
                btnWaterDetail.visibility = View.GONE
                tvWaterTimeDetail.text = getString(R.string.watered)
            }

            btnFertilizeDetail.setOnClickListener {
                currentPlant?.let { viewModel.fertilizePlant(it.id) }
                btnFertilizeDetail.visibility = View.GONE
                tvFertilizeTimeDetail.text = getString(R.string.fertilized)
            }

            btnCheckGrowthDetail.setOnClickListener {
                val intent = Intent(this@DetailActivity, CheckGrowthActivity::class.java)
                intent.putExtra("PLANT_CURRENT_GROWTH", currentPlant?.currentGrowth)
                startActivity(intent)
            }

            btnDeleteDetail.setOnClickListener {
                currentPlant?.let {
                    viewModel.deleteById(it.id)
                    val intent = Intent(this@DetailActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}