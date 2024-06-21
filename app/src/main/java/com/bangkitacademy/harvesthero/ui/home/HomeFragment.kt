package com.bangkitacademy.harvesthero.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bangkitacademy.harvesthero.R
import com.bangkitacademy.harvesthero.databinding.FragmentHomeBinding
import com.bangkitacademy.harvesthero.ui.ViewModelFactory

class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireActivity().application)
    }
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAction()
    }

    private fun setAction() {
        binding.apply {
            viewModel.wateredPlantsCount.observe(viewLifecycleOwner) { count ->
                tvWaterHome.text = getPlantCountText(count)
            }

            viewModel.sickPlantsCount.observe(viewLifecycleOwner) { count ->
                tvSickHome.text = getPlantCountText(count)
            }

            viewModel.fertilizedPlantsCount.observe(viewLifecycleOwner) { count ->
                tvFertilizeHome.text = getPlantCountText(count)
            }
        }

    }

    private fun getPlantCountText(count: Int): String {
        return if (count == 1) {
            getString(R.string.plant_singular, count)
        } else {
            getString(R.string.plant_plural, count)
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}