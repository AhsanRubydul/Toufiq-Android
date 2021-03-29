package com.shahcement.tawfiq.fragment

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.shahcement.tawfiq.AppConstants
import com.shahcement.tawfiq.R
import com.shahcement.tawfiq.adapter.PrayerAdapter
import com.shahcement.tawfiq.adapter.RamadanAdapter
import com.shahcement.tawfiq.data.db.DataRepository
import com.shahcement.tawfiq.data.db.entity.District
import com.shahcement.tawfiq.data.db.entity.Ramadan
import com.shahcement.tawfiq.data.preference.PrefConstants
import com.shahcement.tawfiq.data.preference.PreferenceHelper
import com.shahcement.tawfiq.databinding.FragmentRamadanBinding
import com.shahcement.tawfiq.model.Prayer
import java.util.*

class RamadanFragment : Fragment() {

    private val districts: MutableList<District> = mutableListOf()
    private lateinit var districtAdapter: ArrayAdapter<District>

    private val ramadans: MutableList<Ramadan> = mutableListOf()
    private lateinit var ramadanAdapter: RamadanAdapter

    private var _binding: FragmentRamadanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRamadanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initVariable()
        initView()
        initListener()
    }

    private fun initVariable() {
        districtAdapter = ArrayAdapter(requireContext(), R.layout.item_spinner, districts)
        ramadanAdapter = RamadanAdapter(ramadans)
    }

    private fun initView() {
        binding.spinnerDistrict.adapter = districtAdapter
        binding.spinnerDistrict.setSelection(PreferenceHelper.getInt(PrefConstants.SELECTED_DISTRICT_POS, 0))

        // get districts
        districts.addAll(DataRepository.getInstance().getDistricts())
        districtAdapter.notifyDataSetChanged()

        // set recyclerview
        binding.rvRamadanTimes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRamadanTimes.setHasFixedSize(true)
        binding.rvRamadanTimes.adapter = ramadanAdapter
    }

    private fun initListener() {
        binding.spinnerDistrict.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    PreferenceHelper.put(PrefConstants.SELECTED_DISTRICT_POS, p2)
                    getRamadanData()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }
    }

    private fun getRamadanData() {
        val selectedId =
            if (binding.spinnerDistrict.selectedItemPosition == -1) 0 else binding.spinnerDistrict.selectedItemPosition

        val todaysRamadan = DataRepository.getInstance().getRamadanTime(
            districts[selectedId].district_id, DateFormat.format("yyyy-MM-dd", Date()).toString()
        )

        ramadans.clear()
        ramadanAdapter.notifyDataSetChanged()

        ramadans.addAll(DataRepository.getInstance().getAllRamadanTime(districts[selectedId].district_id))
        ramadanAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}