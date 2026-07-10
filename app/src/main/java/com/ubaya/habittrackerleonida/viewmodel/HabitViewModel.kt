package com.ubaya.habittrackerleonida.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ubaya.habittrackerleonida.model.Habit
import com.ubaya.habittrackerleonida.model.FileHelper
import com.ubaya.habittrackerleonida.model.HabitDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HabitViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    val loginStatusLD = MutableLiveData<Boolean>()
    val habitListLD = MutableLiveData<ArrayList<Habit>>()
    val habitLD = MutableLiveData<Habit>()

    fun checkLogin(user: String, pass: String) {
        launch {
            val db = HabitDatabase.getDatabase(getApplication())
            val result = db.habitDao().login(user, pass)
            loginStatusLD.postValue(result != null)
        }
    }
    fun refresh() {
        launch {
            val db = HabitDatabase.getDatabase(getApplication())
            val list = ArrayList(db.habitDao().selectAllHabit())
            habitListLD.postValue(list)
        }
    }
    fun fetch(id: Int) {
        launch {
            val db = HabitDatabase.getDatabase(getApplication())
            val habit = db.habitDao().selectHabit(id)
            habitLD.postValue(habit)
        }
    }
    fun updateHabit(habit: Habit) {
        launch {
            val db = HabitDatabase.getDatabase(getApplication())
            db.habitDao().updateHabit(habit)
            refresh()
        }
    }
}