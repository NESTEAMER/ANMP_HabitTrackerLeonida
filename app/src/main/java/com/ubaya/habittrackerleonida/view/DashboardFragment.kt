package com.ubaya.habittrackerleonida.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.habittrackerleonida.R
import com.ubaya.habittrackerleonida.viewmodel.HabitViewModel

class DashboardFragment : Fragment() {

    private lateinit var viewModel: HabitViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView =
            view.findViewById<RecyclerView>(R.id.recyclerHabit)

        recyclerView.layoutManager =
            LinearLayoutManager(requireContext())

        viewModel =
            ViewModelProvider(this)[HabitViewModel::class.java]

        viewModel.loadDummyData()

        viewModel.habitListLD.observe(viewLifecycleOwner) {

            val adapter = HabitAdapter(it)
            recyclerView.adapter = adapter
        }
    }
}