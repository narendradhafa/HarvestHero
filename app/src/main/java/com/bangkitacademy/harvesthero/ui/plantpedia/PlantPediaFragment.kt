package com.bangkitacademy.harvesthero.ui.plantpedia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bangkitacademy.harvesthero.databinding.FragmentPlantPediaBinding
import com.bangkitacademy.harvesthero.ui.ViewModelFactory

class PlantPediaFragment : Fragment() {

    private val viewModel by viewModels<PlantPediaViewModel> {
        ViewModelFactory.getInstance(requireActivity().application)
    }
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