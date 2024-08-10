package ca.georgiancollage.assignment04

import androidx.recyclerview.widget.RecyclerView
import ca.georgiancollage.assignment04.databinding.TextRowItemsBinding

class TASKShowViewHolder(private val binding: TextRowItemsBinding) : RecyclerView.ViewHolder(binding.root) {

    // Binds the data from a TVShow object to the views in the ViewHolder.
    fun bind(taskShow: TASKShow) {
        // Set the title of the task to the TextView
        binding.textViewTitle.text = taskShow.title

        // Set the due date and time of the task to the TextView
        binding.textViewTime.text = "${taskShow.dueDate} ${taskShow.dueTime}"

        // Set the task type to the TextView
        binding.textViewType.text = taskShow.taskType

        // Set the state of the task completion to the Switch
        binding.completedSwitch.isChecked = taskShow.isCompleted
    }
}