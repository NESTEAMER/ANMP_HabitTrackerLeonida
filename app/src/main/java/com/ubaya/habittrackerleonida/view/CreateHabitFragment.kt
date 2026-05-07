package com.ubaya.habittrackerleonida.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ubaya.habittrackerleonida.R
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ubaya.habittrackerleonida.model.Habit
import com.ubaya.habittrackerleonida.viewmodel.HabitViewModel

class CreateHabitFragment : Fragment() {

    private lateinit var viewModel: HabitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_habit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[HabitViewModel::class.java]

        val btnCreate = view.findViewById<Button>(R.id.btnCreate)

        btnCreate.setOnClickListener {

            val name = view.findViewById<EditText>(R.id.txtInputName).text.toString()
            val desc = view.findViewById<EditText>(R.id.txtDescription).text.toString()
            val goalText =
                view.findViewById<EditText>(R.id.txtGoal)
                    .text.toString()

            val goal =
                if (goalText.isEmpty()) 1 else goalText.toInt()
            val unit = view.findViewById<EditText>(R.id.txtUnit).text.toString()

            val habit = Habit(
                id = System.currentTimeMillis().toString(),
                name = name,
                description = desc,
                goal = goal,
                unit = unit,
                iconName = "default",
                currentProgress = 0
            )

            viewModel.addHabit(habit)

            findNavController().popBackStack()
        }
    }
    companion object {

    }
}