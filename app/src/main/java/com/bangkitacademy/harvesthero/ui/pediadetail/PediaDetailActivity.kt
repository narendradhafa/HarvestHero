package com.bangkitacademy.harvesthero.ui.pediadetail

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bangkitacademy.harvesthero.R
import com.bangkitacademy.harvesthero.databinding.ActivityPediaDetailBinding
import com.bangkitacademy.harvesthero.ui.ViewModelFactory
import com.bangkitacademy.harvesthero.ui.add.AddActivity
import com.bangkitacademy.harvesthero.ui.myplants.MyPlantsFragment
import com.bangkitacademy.harvesthero.ui.plantpedia.PlantPediaFragment

class PediaDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPediaDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPediaDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupAction()
    }

    private fun setupAction() {
        binding.apply {
            btnPediadetailAdd.setOnClickListener {
                val intent = Intent(this@PediaDetailActivity, AddActivity::class.java)
                //send plant id
                startActivity(intent)
            }
        }

            binding.apply {
                btnBackPedia.setOnClickListener {
                    val intent = Intent(this@PediaDetailActivity, PlantPediaFragment::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }
            }

    }

}