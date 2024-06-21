package com.bangkitacademy.harvesthero.ui.plantpedia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkitacademy.harvesthero.data.local.entity.Plant
import com.bangkitacademy.harvesthero.databinding.FragmentPlantPediaBinding
import com.bangkitacademy.harvesthero.ui.ViewModelFactory
import com.bangkitacademy.harvesthero.ui.pediadetail.PediaDetailActivity

class PlantPediaFragment : Fragment() {

    private val viewModel by viewModels<PlantPediaViewModel> {
        ViewModelFactory.getInstance(requireActivity().application)
    }

    private lateinit var binding: FragmentPlantPediaBinding
    private lateinit var plantPediaAdapter: PlantPediaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPlantPediaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("PlantPediaFragment", "onViewCreated called")

        plantPediaAdapter = PlantPediaAdapter { selectedPlant -> onItemClick(selectedPlant) }

        setupRecyclerView()
        observeViewModel()
    }


    private fun setupRecyclerView() {
        binding.rvPedia.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = plantPediaAdapter
            Log.d("PlantPediaFragment", "Adapter set: ${adapter != null}")
        }
    }

    private fun observeViewModel() {
        viewModel.plants.observe(viewLifecycleOwner) { plants ->
            Log.d("PlantPediaFragment", "Received plants: ${plants.size}")
            plantPediaAdapter.submitList(plants)
        }
    }

    private fun onItemClick(plant: Plant) {
        navigateToDetailScreen(plant.id)
    }

    private fun navigateToDetailScreen(plantId: Int) {
        val intent = Intent(requireContext(), PediaDetailActivity::class.java)
        intent.putExtra("PLANT_ID", plantId)
        startActivity(intent)
    }

    companion object {
        fun newInstance() = PlantPediaFragment()
    }
}