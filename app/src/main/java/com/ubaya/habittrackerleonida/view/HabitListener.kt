package com.ubaya.habittrackerleonida.view

import com.ubaya.habittrackerleonida.model.Habit

interface HabitListener {
    fun onPlusClick(habit: Habit)
    fun onMinusClick(habit: Habit)
    fun onHabitClick(habit: Habit)
}