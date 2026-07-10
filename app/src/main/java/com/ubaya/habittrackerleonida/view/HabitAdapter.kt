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
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.ubaya.habittrackerleonida.databinding.ItemHabitBinding
import com.ubaya.habittrackerleonida.view.HabitListener

class HabitAdapter(private val habitList: ArrayList<Habit>,
    private val listener: HabitListener)
    : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    class HabitViewHolder(val binding: ItemHabitBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup,
        viewType: Int): HabitViewHolder {

        val binding: ItemHabitBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_habit,
                parent,
                false
            )

        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {

        val habit = habitList[position]
        holder.binding.habit = habit
        holder.binding.listener = listener

        holder.binding.txtProgress.text =  "${habit.currentProgress} / ${habit.goal} ${habit.unit}"

        holder.binding.progressHabit.max = habit.goal
        holder.binding.progressHabit.progress = habit.currentProgress

        //icon fix thingy
        val context = holder.itemView.context
        val resourceId = context.resources.getIdentifier(habit.iconName, "drawable", context.packageName)
        holder.binding.imgIcon.setImageResource(resourceId)


        //status
        if (habit.currentProgress >= habit.goal) {
            holder.binding.txtStatus.text = "Completed"
            holder.binding.btnPlus.isEnabled = false
        } else {
            holder.binding.txtStatus.text = "In Progress"
            holder.binding.btnPlus.isEnabled = true
        }


        //btn plus
        //holder.binding.btnPlus.setOnClickListener {
            //if (habit.currentProgress < habit.goal) {
                //habit.currentProgress++
                //notifyItemChanged(position)
            //}
        //}


        //btn min
        //holder.binding.btnMinus.setOnClickListener {
           // if (habit.currentProgress > 0) {
                //habit.currentProgress--
                //notifyItemChanged(position)
            //}
        //}
    }

    override fun getItemCount(): Int {
        return habitList.size
    }
}