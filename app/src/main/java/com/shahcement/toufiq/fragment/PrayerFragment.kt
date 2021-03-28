package com.shahcement.toufiq.fragment

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.shahcement.toufiq.AppConstants
import com.shahcement.toufiq.R
import com.shahcement.toufiq.adapter.PrayerAdapter
import com.shahcement.toufiq.databinding.FragmentPrayerBinding
import com.shahcement.toufiq.db.DataRepository
import com.shahcement.toufiq.db.entity.District
import com.shahcement.toufiq.model.Prayer
import java.text.SimpleDateFormat
import java.util.*


class PrayerFragment : Fragment() {
    private val sdf = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("bn", "BD"))

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
        initListener()
        getWaktData()
    }

    private fun initVariable() {
        districtAdapter = ArrayAdapter(requireContext(), R.layout.item_spinner, districts)
        prayerAdapter = PrayerAdapter(prayers)
    }

    private fun initView() {
        binding.tvDate.text = sdf.format(Date()).toString()

        binding.spinnerDistrict.adapter = districtAdapter
        binding.spinnerDistrict.setSelection(1)

        binding.rvPrayerTimes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPrayerTimes.setHasFixedSize(true)
        binding.rvPrayerTimes.adapter = prayerAdapter

        districts.addAll(DataRepository.getInstance().getDistricts())
        districtAdapter.notifyDataSetChanged()
    }

    private fun initListener() {
        binding.spinnerDistrict.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    getWaktData()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }
    }

    private fun getWaktData() {
        val selectedId =
            if (binding.spinnerDistrict.selectedItemPosition == -1) 0 else binding.spinnerDistrict.selectedItemPosition

        val wakt = DataRepository.getInstance().getWakt(
            districts[selectedId].district_id, DateFormat.format("yyyy-MM-dd", Date()).toString()
        )

        prayers.clear()
        prayerAdapter.notifyDataSetChanged()

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