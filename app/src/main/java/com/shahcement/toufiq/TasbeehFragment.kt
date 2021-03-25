package com.shahcement.toufiq

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shahcement.toufiq.databinding.FragmentTasbeehBinding


class TasbeehFragment : Fragment() {

    private var count : Int = 0

    private var _binding: FragmentTasbeehBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTasbeehBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvCounter.text = count.toString()

        binding.ivCounter.setOnClickListener {
            count++
            binding.tvCounter.text = count.toString()
        }

        binding.lytReset.setOnClickListener {
            count = 0
            binding.tvCounter.text = count.toString()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}