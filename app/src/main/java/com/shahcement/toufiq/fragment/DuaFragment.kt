package com.shahcement.toufiq.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.shahcement.toufiq.Utils
import com.shahcement.toufiq.adapter.CommonAdapter
import com.shahcement.toufiq.databinding.FragmentDuaBinding
import com.shahcement.toufiq.model.CommonModel
import org.json.JSONArray
import org.json.JSONException

class DuaFragment : Fragment() {

    private lateinit var adapter: CommonAdapter
    private val models  = mutableListOf<CommonModel>()

    private var _binding : FragmentDuaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentDuaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CommonAdapter(models)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter

        parseData()
    }

    private fun parseData() {
        Utils.loadJSONFromAsset(requireActivity().assets, "dua.json")?.let {
            try {
                val array = JSONArray(it)
                for (i in 0 until array.length()) {
                    val obj = array.getJSONObject(i)
                    models.add(CommonModel(obj.getString("dua")))
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}