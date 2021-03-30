package com.shahcement.tawfiq.fragment

import android.graphics.Color
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.shahcement.tawfiq.R
import com.shahcement.tawfiq.adapter.RamadanAdapter
import com.shahcement.tawfiq.data.db.DataRepository
import com.shahcement.tawfiq.data.db.entity.District
import com.shahcement.tawfiq.data.db.entity.Ramadan
import com.shahcement.tawfiq.data.preference.PrefConstants
import com.shahcement.tawfiq.data.preference.PreferenceHelper
import com.shahcement.tawfiq.databinding.FragmentRamadanBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class RamadanFragment : Fragment() {

    private val sdf = SimpleDateFormat("dd-MM-yyyy", Locale("bn", "BD"))

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
        binding.spinnerDistrict.setSelection(
            PreferenceHelper.getInt(
                PrefConstants.SELECTED_DISTRICT_POS,
                0
            )
        )

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
                    (p0?.getChildAt(0) as TextView).setTextColor(Color.BLACK)
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

        todaysRamadan?.let {
            binding.tvIftarTime.text = it.iftarTime.substringBefore(" PM")
            binding.tvSahriTime.text = it.sahriTime.substringBefore(" AM")

            val format = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            val date = try {
                format.parse(it.date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            if (date != null) {
                binding.tvToday.text = getString(R.string.todays_schedule, sdf.format(date))
            } else {
                binding.tvToday.text = getString(R.string.todays_schedule, it.date)
            }
        }

        ramadans.clear()
        ramadanAdapter.notifyDataSetChanged()

        val list = mutableListOf<Ramadan>()

        val remoteConfig = Firebase.remoteConfig

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(requireActivity()) {

                val start = remoteConfig.getString("ramadan_start_date")
                val end = remoteConfig.getString("ramadan_end_date")

                val format = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                try {
                    val startDate = format.parse(start)
                    val endDate = format.parse(end)

                    if (startDate != null && endDate != null) {
                        DataRepository.getInstance().getAllRamadanTime(districts[selectedId].district_id).forEach {
                            if (isValidDate(it.date, startDate, endDate)) {
                                list.add(it)
                            }
                        }
                    }
                } catch (e: ParseException) {
                    e.printStackTrace()
                }

                ramadans.addAll( if (list.isNotEmpty()) {
                    list
                } else
                    DataRepository.getInstance().getAllRamadanTime(districts[selectedId].district_id)
                )

                ramadanAdapter.notifyDataSetChanged()
                binding.progressBar.visibility = View.GONE
            }
    }

    private fun isValidDate(date: String, startDate : Date, endDate: Date): Boolean {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val dt = format.parse(date)
        return try {
            (dt.equals(startDate) || dt.after(startDate)) // is startDate or after startDate
                    && (dt.equals(endDate) || dt.before(endDate)) // is endDate or before endDate
        } catch (e: ParseException) {
            false
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}