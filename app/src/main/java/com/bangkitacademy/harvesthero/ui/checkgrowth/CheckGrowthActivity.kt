package com.bangkitacademy.harvesthero.ui.checkgrowth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bangkitacademy.harvesthero.R
import com.bangkitacademy.harvesthero.databinding.ActivityCheckGrowthBinding
import com.bangkitacademy.harvesthero.helper.ImageClassifierHelper
import com.bangkitacademy.harvesthero.ui.detail.DetailActivity
import com.bangkitacademy.harvesthero.ui.myplants.MyPlantsFragment

class CheckGrowthActivity : AppCompatActivity(), ImageClassifierHelper.ClassifierListener {

    private lateinit var binding: ActivityCheckGrowthBinding
    private lateinit var imageClassifierHelper: ImageClassifierHelper

    private var currentImageUri: Uri? = null
    private var plantCurrentGrowth: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCheckGrowthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        plantCurrentGrowth = intent.getStringExtra("PLANT_CURRENT_GROWTH")
        if (plantCurrentGrowth != null) binding.tvCheckedCurrentGrowth.text = plantCurrentGrowth

        imageClassifierHelper = ImageClassifierHelper(this, this)
        setAction()
    }

    private fun setAction() {
        binding.apply {
            btnCheckGrowth.setOnClickListener {
                launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
        }
    }

    private var launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
            classifyImage(uri)
        } else {
            Log.d("Photo Picker", "No Media Selected")
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.imgCheckGrowth.setImageURI(it)
        }
    }

    private fun classifyImage(uri: Uri) {
        imageClassifierHelper.classifyStaticImage(uri)
    }

    override fun onResults(results: List<Float>?, inferenceTime: Long) {
        results?.let {
            // Handle the classification results here
            Log.d("Classification Results", it.joinToString())
            Log.d("Inference Time", "$inferenceTime ms")

            val topResult = it.firstOrNull()
            binding.tvCheckedCurrentGrowth.text = topResult?.toString() ?: "No result"
        }
    }

    override fun onError(error: String) {
        Log.e("Image Classification", error)
    }
}