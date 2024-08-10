package ca.georgiancollage.assignment04

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ca.georgiancollage.assignment04.databinding.TextRowItemsBinding

class TASKShowListAdapter (private val onItemClicked: (TASKShow) -> Unit) :
    ListAdapter<TASKShow, TASKShowViewHolder>(TASKShowComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TASKShowViewHolder {
        // Inflate the item layout and create a binding object
        val binding = TextRowItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        // Return a new ViewHolder instance with the binding object
        return TASKShowViewHolder(binding)
    }

    // Called by the RecyclerView to display the data at the specified position.
    override fun onBindViewHolder(holder: TASKShowViewHolder, position: Int) {
        // Get the TVShow item at the given position
        val current = getItem(position)
        // Bind the TVShow item to the ViewHolder
        holder.bind(current)
        // Set a click listener on the itemView to handle item clicks
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
    }
}