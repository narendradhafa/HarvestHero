package com.bangkitacademy.harvesthero.ui.plantpedia

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bangkitacademy.harvesthero.R
import com.bangkitacademy.harvesthero.databinding.FragmentPlantPediaBinding

class PlantPediaFragment : Fragment() {

    private val viewModel: PlantPediaViewModel by viewModels()
    private lateinit var binding: FragmentPlantPediaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPlantPediaBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = PlantPediaFragment()
    }
}