package ca.georgiancollage.assignment04

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.georgiancollage.assignment04.databinding.TextRowItemsBinding

class FirstAdapter(private val dataSet: Array<TASKShow>) :
    RecyclerView.Adapter<FirstAdapter.ViewHolder>() {
    class ViewHolder(val binding: TextRowItemsBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
// Inflate the layout with view binding
        val binding = TextRowItemsBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
// Use view binding to set the text
        /*viewHolder.binding.title.text = dataSet[position].title
        viewHolder.binding.subTitle.text = dataSet[position].subTitle*/

        viewHolder.binding.textViewTitle.text = dataSet[position].title
        viewHolder.binding.textViewTime.text = "${dataSet[position].dueDate} ${dataSet[position].dueTime}"
        viewHolder.binding.textViewType.text = dataSet[position].taskType
        viewHolder.binding.completedSwitch.isChecked = dataSet[position].isCompleted
    }
    override fun getItemCount() = dataSet.size
}