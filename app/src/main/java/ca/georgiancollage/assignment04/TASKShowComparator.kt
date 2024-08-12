package ca.georgiancollage.assignment04

import androidx.recyclerview.widget.DiffUtil

class TASKShowComparator : DiffUtil.ItemCallback<TASKShow>()
{

    override fun areItemsTheSame(oldItem: TASKShow, newItem: TASKShow): Boolean
    {
        return oldItem.id == newItem.id
    }


    override fun areContentsTheSame(oldItem: TASKShow, newItem: TASKShow): Boolean
    {
        return oldItem == newItem
    }
}