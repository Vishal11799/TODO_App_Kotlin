package ca.georgiancollage.assignment04

import androidx.recyclerview.widget.DiffUtil

class TASKShowComparator : DiffUtil.ItemCallback<TASKShow>()
{
    // This method checks if two items represent the same entity by comparing their IDs.
    override fun areItemsTheSame(oldItem: TASKShow, newItem: TASKShow): Boolean
    {
        return oldItem.id == newItem.id
    }

    // this method checks if the contents of two items are the same by comparing their properties.
    override fun areContentsTheSame(oldItem: TASKShow, newItem: TASKShow): Boolean
    {
        return oldItem == newItem
    }
}