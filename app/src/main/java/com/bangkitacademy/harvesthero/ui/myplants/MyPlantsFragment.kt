package com.bangkitacademy.harvesthero.ui.myplants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkitacademy.harvesthero.databinding.FragmentMyPlantsBinding
import com.bangkitacademy.harvesthero.ui.ViewModelFactory

class MyPlantsFragment : Fragment() {

    private val viewModel by viewModels<MyPlantsViewModel> {
        ViewModelFactory.getInstance(requireActivity().application)
    }
    private lateinit var binding: FragmentMyPlantsBinding
    private lateinit var adapter: MyPlantsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMyPlantsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MyPlantsAdapter()
        binding.rvMyPlants.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMyPlants.adapter = adapter

        viewModel.plants.observe(viewLifecycleOwner) { plants ->
            plants?.let {
                adapter.submitList(it)
            }
        }
    }

    companion object {
        fun newInstance() = MyPlantsFragment()
    }
}