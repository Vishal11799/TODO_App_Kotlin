package ca.georgiancollage.assignment04

import androidx.recyclerview.widget.DiffUtil

/**
 * TASKShowComparator is a utility class that provides comparison logic for TASKShow items.
 * It is used by the RecyclerView's adapter to efficiently update the list by determining
 * if items are the same or if their contents have changed.
 */
class TASKShowComparator : DiffUtil.ItemCallback<TASKShow>() {

    /**
     * Determines whether two TASKShow items represent the same task by comparing their IDs.
     *
     * @param oldItem The previous TASKShow item.
     * @param newItem The new TASKShow item.
     * @return True if the items have the same ID, false otherwise.
     */
    override fun areItemsTheSame(oldItem: TASKShow, newItem: TASKShow): Boolean {
        return oldItem.id == newItem.id
    }

    /**
     * Checks whether the contents of two TASKShow items are the same.
     * This method is called if areItemsTheSame() returns true.
     *
     * @param oldItem The previous TASKShow item.
     * @param newItem The new TASKShow item.
     * @return True if the contents of the items are identical, false otherwise.
     */
    override fun areContentsTheSame(oldItem: TASKShow, newItem: TASKShow): Boolean {
        return oldItem == newItem
    }
}
