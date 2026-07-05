package com.ubaya.habittrackerleonida.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Habit(
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "goal")
    val goal: Int,
    @ColumnInfo(name = "unit")
    val unit: String,
    @ColumnInfo(name = "iconName")
    val iconName: String,
    @ColumnInfo(name = "currentProgress")
    var currentProgress: Int = 0
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}


//data class Habit ( //UTS VER, perserved jaga jaga
//    val id: String,
//    val name: String,
//    val description: String,
//    val goal: Int,
//    val unit: String,
//    val iconName: String,
//    var currentProgress: Int = 0 //temp? ubah kalo perlu. Var= mutable
//)