package com.bangkitacademy.harvesthero.ui.plantpedia

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bangkitacademy.harvesthero.R

class PlantPediaFragment : Fragment() {

    private val viewModel: PlantPediaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(R.layout.fragment_plant_pedia, container, false)
    }

    companion object {
        fun newInstance() = PlantPediaFragment()
    }
}