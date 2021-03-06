package com.example.nnv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.nnvlib.NnvViewModel
import com.example.nnv.databinding.PointFragmentBinding

class PointFragment : Fragment() {

    private lateinit var binding : PointFragmentBinding
    private var viewModel = activityViewModels<NnvViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.point_fragment, container, false)
        binding.point = viewModel.value.longTapPoint.value?.peekContent()
        viewModel.value.longTapPoint.observe(viewLifecycleOwner, Observer { p ->
            p.getContentIfNotHandled()?.let {
                findNavController().navigate(R.id.toPont)
            }
        })
        binding.frame.setOnClickListener {
            findNavController().navigate(R.id.toPointDetail)
        }
        binding.toRoute.setOnClickListener {
            findNavController().navigate(R.id.toRoute)
        }
        return binding.root
    }

}