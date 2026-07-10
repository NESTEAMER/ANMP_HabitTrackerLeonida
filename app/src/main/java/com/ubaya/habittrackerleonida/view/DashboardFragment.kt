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
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ubaya.habittrackerleonida.model.Habit

class DashboardFragment : Fragment(), HabitListener {

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

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerHabit)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProvider(requireActivity())[HabitViewModel::class.java]
        viewModel.refresh()

        viewModel.habitListLD.observe(viewLifecycleOwner) {
            val adapter = HabitAdapter(it, this)
            recyclerView.adapter = adapter
        }

        //viewModel.loadDummyData()


//        viewModel.habitListLD.observe(viewLifecycleOwner) {
//
//            val adapter = HabitAdapter(it)
//            recyclerView.adapter = adapter
//        }

        val fabAdd = view.findViewById<FloatingActionButton>(R.id.fabAdd)

        fabAdd.setOnClickListener {
            findNavController().navigate(
                R.id.actionDashboardCreateHabitFragment
            )
        }
    }

    override fun onPlusClick(habit: Habit) {
        if (habit.currentProgress < habit.goal) {
            habit.currentProgress++
            viewModel.updateHabit(habit)
        }
    }

    override fun onMinusClick(habit: Habit) {
        if (habit.currentProgress > 0) {
            habit.currentProgress--
            viewModel.updateHabit(habit)
        }
    }

    override fun onHabitClick(habit: Habit) {
        val action =
            DashboardFragmentDirections
                .actionDashboardEditHabitFragment(habit.id)

        findNavController().navigate(action)
    }
}