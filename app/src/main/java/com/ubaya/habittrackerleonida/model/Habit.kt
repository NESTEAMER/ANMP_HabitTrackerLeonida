package com.ubaya.habittrackerleonida.model

data class Habit ( //Sementara, boleh ganti sesuai keperluan V
    val id: String,
    val name: String,
    val description: String,
    val goal: Int,
    val unit: String,
    val iconName: String,
    var currentProgress: Int = 0 //temp? ubah kalo perlu. Var= mutable
)