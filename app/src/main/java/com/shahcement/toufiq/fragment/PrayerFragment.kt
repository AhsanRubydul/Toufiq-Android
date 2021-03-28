package com.shahcement.toufiq.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.shahcement.toufiq.AppConstants
import com.shahcement.toufiq.R
import com.shahcement.toufiq.adapter.PrayerAdapter
import com.shahcement.toufiq.databinding.FragmentPrayerBinding
import com.shahcement.toufiq.db.DataRepository
import com.shahcement.toufiq.db.entity.District
import com.shahcement.toufiq.db.entity.Wakt
import com.shahcement.toufiq.model.Prayer


class PrayerFragment : Fragment() {

    private val districts: MutableList<District> = mutableListOf()
    private lateinit var districtAdapter: ArrayAdapter<District>

    private val prayers: MutableList<Prayer> = mutableListOf()
    private lateinit var prayerAdapter: PrayerAdapter

    private var _binding: FragmentPrayerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPrayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initVariable()
        initView()
        getData()
    }

    private fun initVariable() {
        districtAdapter = ArrayAdapter(requireContext(), R.layout.item_spinner, districts)
        prayerAdapter = PrayerAdapter(prayers)
    }

    private fun initView() {
        binding.inputLayout.adapter = districtAdapter
        binding.inputLayout.setSelection(1)

        binding.rvPrayerTimes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPrayerTimes.setHasFixedSize(true)
        binding.rvPrayerTimes.adapter = prayerAdapter
    }

    private fun getData() {
        val d = DataRepository.getInstance().getDistricts()
        districts.addAll(d)
        districtAdapter.notifyDataSetChanged()

        val selectedId =
            if (binding.inputLayout.selectedItemPosition == -1) 0 else binding.inputLayout.selectedItemPosition

        val wakt = DataRepository.getInstance().getWakt(
            d[selectedId].district_id, "2021-01-01"
        )

        wakt?.let {
            prayers.add(Prayer(AppConstants.FAZAR, R.drawable.ic_fazar, it.fazarTime))
            prayers.add(Prayer(AppConstants.ZOHAR, R.drawable.ic_zohar, it.zoharTime))
            prayers.add(Prayer(AppConstants.ASR, R.drawable.ic_asr, it.asarTime))
            prayers.add(Prayer(AppConstants.MAGHRIB, R.drawable.ic_maghrib, it.maghribTime))
            prayers.add(Prayer(AppConstants.ISHA, R.drawable.ic_isha, it.ishaTime))
        }

        prayerAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}