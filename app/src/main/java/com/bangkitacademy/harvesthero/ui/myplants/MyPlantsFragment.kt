package com.bangkitacademy.harvesthero.ui.myplants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bangkitacademy.harvesthero.databinding.FragmentMyPlantsBinding
import com.bangkitacademy.harvesthero.ui.ViewModelFactory

class MyPlantsFragment : Fragment() {

    private val viewModel by viewModels<MyPlantsViewModel> {
        ViewModelFactory.getInstance(requireActivity().application)
    }
    private lateinit var binding: FragmentMyPlantsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMyPlantsBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = MyPlantsFragment()
    }
}