package com.shahcement.toufiq.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.shahcement.toufiq.R
import com.shahcement.toufiq.databinding.FragmentPrayerBinding
import com.shahcement.toufiq.db.DataRepository
import com.shahcement.toufiq.db.entity.District


class PrayerFragment : Fragment() {

    private val districts: MutableList<District> = mutableListOf()
    private lateinit var districtAdapter: ArrayAdapter<District>

    private var _binding: FragmentPrayerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentPrayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initVariable()
        initView()
    }

    private fun initVariable() {
        districtAdapter = ArrayAdapter(
            requireContext(),
            R.layout.item_spinner, districts
        )
    }

    private fun initView() {
        binding.inputLayout.adapter = districtAdapter

        districts.addAll(DataRepository.getInstance().getDistricts())
        districtAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}