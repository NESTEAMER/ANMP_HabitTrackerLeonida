package com.ubaya.habittrackerleonida.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.habittrackerleonida.R
import com.ubaya.habittrackerleonida.model.Habit
import android.widget.ProgressBar
import android.widget.Button

class HabitAdapter(private val habitList: ArrayList<Habit>)
    : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    class HabitViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtDesc: TextView = itemView.findViewById(R.id.textView2)
        val txtProgress: TextView = itemView.findViewById(R.id.txtProgress)
        val progressBar: ProgressBar = itemView.findViewById(R.id.progressHabit)
        val txtStatus: TextView = itemView.findViewById(R.id.txtStatus)

        val btnPlus: Button = itemView.findViewById(R.id.btnPlus)
        val btnMinus: Button = itemView.findViewById(R.id.btnMinus)
    }

    override fun onCreateViewHolder(parent: ViewGroup,
        viewType: Int): HabitViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_habit, parent, false)

        return HabitViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {

        val habit = habitList[position]

        holder.txtName.text = habit.name
        holder.txtDesc.text = habit.description

        holder.txtProgress.text =  "${habit.currentProgress} / ${habit.goal} ${habit.unit}"

        holder.progressBar.max = habit.goal
        holder.progressBar.progress = habit.currentProgress

        //status
        if (habit.currentProgress >= habit.goal) {
            holder.txtStatus.text = "Completed"
            holder.btnPlus.isEnabled = false
        } else {
            holder.txtStatus.text = "In Progress"
            holder.btnPlus.isEnabled = true
        }


        //btn plus
        holder.btnPlus.setOnClickListener {
            if (habit.currentProgress < habit.goal) {
                habit.currentProgress++
                notifyItemChanged(position)
            }
        }


        //btn min
        holder.btnMinus.setOnClickListener {
            if (habit.currentProgress > 0) {
                habit.currentProgress--
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return habitList.size
    }
}