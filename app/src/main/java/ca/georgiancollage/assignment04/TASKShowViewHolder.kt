package ca.georgiancollage.assignment04

import androidx.recyclerview.widget.RecyclerView
import ca.georgiancollage.assignment04.databinding.TextRowItemsBinding

/**
 * TASKShowViewHolder is a RecyclerView.ViewHolder that binds a TASKShow object to its corresponding UI components.
 * It handles the display of task details and provides interaction logic for clicks and switches within the item view.
 *
 * @param binding The binding object that provides access to the UI components of the item view.
 * @param onItemClicked Lambda function to be invoked when a TASKShow item is clicked.
 * @param onEditButtonClicked Lambda function to be invoked when the edit button of a TASKShow item is clicked.
 */
class TASKShowViewHolder(
    private val binding: TextRowItemsBinding,
    private val onItemClicked: (TASKShow) -> Unit,
    private val onEditButtonClicked: (TASKShow) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    /**
     * Binds the TASKShow data to the UI components in the item view.
     * It sets the task details and manages click events for the item and edit button.
     *
     * @param taskShow The TASKShow object containing the task details to be displayed.
     */
    fun bind(taskShow: TASKShow) {
        // Set task details in the UI
        binding.textViewTitle.text = taskShow.title ?: "Untitled Task"
        binding.textViewTime.text = "${taskShow.dueDate ?: "No Date"} ${taskShow.dueTime ?: "No Time"}"
        binding.textViewType.text = taskShow.taskType ?: "General"
        binding.completedSwitch.isChecked = taskShow.isCompleted

        // Update the card background color based on completion status
        updateCardBackgroundColor(taskShow.isCompleted)

        // Set up the completion switch listener
        binding.completedSwitch.setOnCheckedChangeListener { _, isChecked ->
            updateCardBackgroundColor(isChecked)
            taskShow.isCompleted = isChecked
        }

        // Set up click listeners for the item and edit button
        binding.root.setOnClickListener {
            onItemClicked(taskShow)
        }

        binding.editButton.setOnClickListener {
            onEditButtonClicked(taskShow)
        }
    }

    /**
     * Updates the background color of the card view based on the task's completion status.
     *
     * @param isCompleted Boolean value indicating whether the task is completed.
     */
    private fun updateCardBackgroundColor(isCompleted: Boolean) {
        val color = if (isCompleted) {
            binding.root.context.getColor(R.color.grey)
        } else {
            binding.root.context.getColor(R.color.item_background)
        }
        binding.cardView.setCardBackgroundColor(color)
    }
}
