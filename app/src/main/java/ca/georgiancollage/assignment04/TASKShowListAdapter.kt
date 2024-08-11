package ca.georgiancollage.assignment04

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ca.georgiancollage.assignment04.databinding.TextRowItemsBinding
class TASKShowListAdapter(
    private val onItemClicked: (TASKShow) -> Unit,
    private val onEditButtonClicked: (TASKShow) -> Unit
) : ListAdapter<TASKShow, TASKShowViewHolder>(TASKShowComparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TASKShowViewHolder {
        val binding = TextRowItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TASKShowViewHolder(binding, onItemClicked, onEditButtonClicked)
    }


    // Called by the RecyclerView to display the data at the specified position.
    override fun onBindViewHolder(holder: TASKShowViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

}