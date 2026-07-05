package com.ubaya.habittrackerleonida.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface HabitDao { //ganti/tambah sesuai keperluan V
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHabit(vararg habit: Habit)

    @Update
    fun updateHabit(habit: Habit)

    @Query("SELECT * FROM habit")
    fun selectAllHabit(): List<Habit>

    @Query("SELECT * FROM habit WHERE id= :id")
    fun selectHabit(id: Int): Habit


    //user stuff sekalian
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(vararg user: User)

    @Query("SELECT * FROM user WHERE username = :user AND password = :pass") //better work this time pls
    fun login(user: String, pass: String): User?
}