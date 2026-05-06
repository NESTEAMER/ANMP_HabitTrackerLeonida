package com.ubaya.habittrackerleonida.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ubaya.habittrackerleonida.model.Habit

class HabitViewModel : ViewModel() {

    val habitListLD = MutableLiveData<ArrayList<Habit>>()

    fun loadDummyData() {
        val list = arrayListOf(
            Habit(
                "1",
                "Olahraga",
                "Latihan pagi",
                8,
                "times",
                "fitness",
                3
            ),

            Habit(
                "2",
                "Belajar",
                "Belajar ANMP",
                5,
                "hours",
                "book",
                3
            ),

            Habit(
                "3",
                "Minum Air",
                "Minum cukup",
                8,
                "glasses",
                "water",
                3
            ),

            Habit(
                "4",
                "Baca Buku",
                "Baca 10 halaman",
                10,
                "pages",
                "book",
                3
            )
        )

        habitListLD.value = list

    }
}