package com.shahcement.tawfiq.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shahcement.tawfiq.AppConstants
import com.shahcement.tawfiq.Utils
import com.shahcement.tawfiq.data.preference.PrefConstants
import com.shahcement.tawfiq.data.preference.PreferenceHelper
import com.shahcement.tawfiq.databinding.FragmentTasbeehBinding


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

        count = PreferenceHelper.getInt(PrefConstants.TASBEEH_COUNT, 0)
        showCount(count)

        binding.ivCounter.setOnClickListener {
            count++
            showCount(count)
        }

        binding.lytReset.setOnClickListener {
            count = 0
            showCount(count)
        }
    }

    private fun showCount(count : Int) {
        binding.tvCounter.text = Utils.showNumberInBangla(count)
        PreferenceHelper.put(PrefConstants.TASBEEH_COUNT, count)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}