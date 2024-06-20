package com.bangkitacademy.harvesthero.ui.myplants

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
import com.bangkitacademy.harvesthero.databinding.FragmentMyPlantsBinding
import com.bangkitacademy.harvesthero.ui.ViewModelFactory
import com.bangkitacademy.harvesthero.ui.detail.DetailActivity

class MyPlantsFragment : Fragment() {

    private val viewModel by viewModels<MyPlantsViewModel> {
        ViewModelFactory.getInstance(requireActivity().application)
    }
    private lateinit var binding: FragmentMyPlantsBinding
    private lateinit var myPlantsAdapter: MyPlantsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMyPlantsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MyPlantsFragment", "onViewCreated called")

        myPlantsAdapter = MyPlantsAdapter { selectedPlant -> onItemClick(selectedPlant) }

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.rvMyPlants.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = myPlantsAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.plants.observe(viewLifecycleOwner) { plants ->
            Log.d("MyPlantsFragment", "Received plants: ${plants.size}")
            myPlantsAdapter.submitList(plants)
        }
    }

    private fun onItemClick(plant: Plant) {
        navigateToDetailScreen(plant.id)
    }

    private fun navigateToDetailScreen(plantId: Int) {
        val intent = Intent(requireContext(), DetailActivity::class.java)
        intent.putExtra("PLANT_ID", plantId)
        startActivity(intent)
    }

    companion object {
        fun newInstance() = MyPlantsFragment()
    }
}