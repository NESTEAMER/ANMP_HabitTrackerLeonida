package com.ubaya.habittrackerleonida.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.habittrackerleonida.R

class HabitAdapter : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    private val habitList = listOf(
        "Olahraga",
        "Belajar",
        "Minum Air",
        "Baca Buku"
    )

    class HabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtDesc: TextView = itemView.findViewById(R.id.textView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_habit, parent, false)
        return HabitViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habitList[position]
        holder.txtName.text = habit
        holder.txtDesc.text = "Ini deskripsi $habit"
    }

    override fun getItemCount(): Int = habitList.size
}