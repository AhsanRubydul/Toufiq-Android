package com.shahcement.tawfiq.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.shahcement.tawfiq.Utils
import com.shahcement.tawfiq.activity.PdfViewerActivity
import com.shahcement.tawfiq.adapter.CommonAdapter
import com.shahcement.tawfiq.databinding.FragmentDuaBinding
import com.shahcement.tawfiq.model.CommonModel
import org.json.JSONArray
import org.json.JSONException

class DuaFragment : Fragment() {

    private lateinit var adapter: CommonAdapter
    private val models = mutableListOf<CommonModel>()

    private var _binding: FragmentDuaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

        adapter.setOnClickListener(object : CommonAdapter.OnClickListener {
            override fun onClick(position: Int) {
                startActivity(
                    Intent(requireContext(), PdfViewerActivity::class.java)
                        .putExtra("file", models[position].file)
                )
            }
        })
    }

    private fun parseData() {
        Utils.loadJSONFromAsset(requireActivity().assets, "dua.json")?.let {
            try {
                val array = JSONArray(it)
                for (i in 0 until array.length()) {
                    val obj = array.getJSONObject(i)
                    models.add(CommonModel(obj.getString("dua"), obj.getString("file")))
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