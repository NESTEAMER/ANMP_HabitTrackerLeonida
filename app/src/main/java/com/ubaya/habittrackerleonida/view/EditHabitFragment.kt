package com.ubaya.habittrackerleonida.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ubaya.habittrackerleonida.databinding.FragmentEditHabitBinding
import com.ubaya.habittrackerleonida.model.Habit
import com.ubaya.habittrackerleonida.viewmodel.HabitViewModel

class EditHabitFragment : Fragment(), EditHabitListener {

    private lateinit var binding: FragmentEditHabitBinding
    private lateinit var viewModel: HabitViewModel
    private val args: EditHabitFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentEditHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[HabitViewModel::class.java]

        binding.listener = this

        viewModel.fetch(args.habitId)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.habitLD.observe(viewLifecycleOwner) {
            binding.habit = it

            binding.txtGoal.setText(it.goal.toString())

            binding.dropdownIcon.setText(it.iconName, false)
        }
    }

    override fun onClick(v: View) {

        val obj = binding.habit as Habit

        val goalText = binding.txtGoal.text.toString()

        obj.goal =
            if (goalText.isEmpty())
                obj.goal
            else
                goalText.toInt()

        obj.iconName = binding.dropdownIcon.text.toString()

        viewModel.updateHabit(obj)

        Toast.makeText(requireContext(),"Habit Updated",Toast.LENGTH_SHORT).show()

        findNavController().popBackStack()
    }
}