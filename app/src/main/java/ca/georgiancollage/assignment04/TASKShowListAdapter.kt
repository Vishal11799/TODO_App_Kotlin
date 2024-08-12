package ca.georgiancollage.assignment04

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ca.georgiancollage.assignment04.databinding.TextRowItemsBinding

/**
 * TASKShowListAdapter is a RecyclerView adapter for displaying a list of TASKShow items.
 * It uses a ListAdapter to efficiently manage the list and updates using DiffUtil.
 * The adapter handles item clicks and edit button clicks by providing the corresponding TASKShow to the provided lambdas.
 *
 * @param onItemClicked Lambda function to be invoked when a TASKShow item is clicked.
 * @param onEditButtonClicked Lambda function to be invoked when the edit button of a TASKShow item is clicked.
 */
class TASKShowListAdapter(
    private val onItemClicked: (TASKShow) -> Unit,
    private val onEditButtonClicked: (TASKShow) -> Unit
) : ListAdapter<TASKShow, TASKShowViewHolder>(TASKShowComparator()) {

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     * This method inflates the item layout and returns a new ViewHolder instance.
     *
     * @param parent The parent ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new TASKShowViewHolder that holds the View for each TASKShow item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TASKShowViewHolder {
        val binding = TextRowItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TASKShowViewHolder(binding, onItemClicked, onEditButtonClicked)
    }

    /**
     * Called by the RecyclerView to display the data at the specified position.
     * This method updates the contents of the ViewHolder to reflect the TASKShow at the given position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the item at the given position.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: TASKShowViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }
}
