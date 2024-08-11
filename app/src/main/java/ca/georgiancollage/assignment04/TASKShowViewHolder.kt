    package ca.georgiancollage.assignment04

    import androidx.recyclerview.widget.RecyclerView
    import ca.georgiancollage.assignment04.databinding.TextRowItemsBinding

    class TASKShowViewHolder(
        private val binding: TextRowItemsBinding,
        private val onItemClicked: (TASKShow) -> Unit,
        private val onEditButtonClicked: (TASKShow) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(taskShow: TASKShow) {
            binding.textViewTitle.text = taskShow.title ?: "Untitled Task"
            binding.textViewTime.text = "${taskShow.dueDate ?: "No Date"} ${taskShow.dueTime ?: "No Time"}"
            binding.textViewType.text = taskShow.taskType ?: "General"
            binding.completedSwitch.isChecked = taskShow.isCompleted

            binding.root.setOnClickListener {
                onItemClicked(taskShow)
            }

            binding.editButton.setOnClickListener {
                onEditButtonClicked(taskShow)
            }
        }
    }
