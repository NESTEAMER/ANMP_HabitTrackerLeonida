package com.ubaya.habittrackerleonida.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ubaya.habittrackerleonida.R
import androidx.navigation.fragment.navArgs
import androidx.lifecycle.ViewModelProvider
import com.ubaya.habittrackerleonida.viewmodel.HabitViewModel
import com.google.android.material.textfield.TextInputEditText
import android.widget.AutoCompleteTextView
import android.widget.Button
import com.ubaya.habittrackerleonida.model.Habit
import androidx.navigation.fragment.findNavController

class EditHabitFragment : Fragment() {
    private val args: EditHabitFragmentArgs by navArgs()
    private lateinit var currentHabit: Habit
    private lateinit var viewModel: HabitViewModel
    private lateinit var txtName: TextInputEditText
    private lateinit var txtDescription: TextInputEditText
    private lateinit var txtGoal: TextInputEditText
    private lateinit var txtUnit: TextInputEditText
    private lateinit var dropdownIcon: AutoCompleteTextView
    private lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_habit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtName = view.findViewById(R.id.txtInputName)
        txtDescription = view.findViewById(R.id.txtDescription)
        txtGoal = view.findViewById(R.id.txtGoal)
        txtUnit = view.findViewById(R.id.txtUnit)
        dropdownIcon = view.findViewById(R.id.dropdownIcon)
        btnSubmit = view.findViewById(R.id.btnCreate)

        val habitId = args.habitId
        viewModel = ViewModelProvider(requireActivity())[HabitViewModel::class.java]
        viewModel.fetch(habitId)
        viewModel.habitLD.observe(viewLifecycleOwner) { habit ->
            currentHabit = habit

            txtName.setText(habit.name)
            txtDescription.setText(habit.description)
            txtGoal.setText(habit.goal.toString())
            txtUnit.setText(habit.unit)
            dropdownIcon.setText(habit.iconName, false)
        }
        btnSubmit.setOnClickListener{
            currentHabit.name = txtName.text.toString()
            currentHabit.description = txtDescription.text.toString()
            currentHabit.goal = txtGoal.text.toString().toInt()
            currentHabit.unit = txtUnit.text.toString()
            currentHabit.iconName = dropdownIcon.text.toString()

            viewModel.updateHabit(currentHabit)
            findNavController().popBackStack()
        }
    }
}