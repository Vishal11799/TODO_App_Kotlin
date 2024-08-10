package ca.georgiancollage.assignment04

import androidx.recyclerview.widget.ListAdapter

class TASKShowListAdapter (private val onItemClicked: (TASKShow) -> Unit) :
    ListAdapter<TASKShow, TASKShowViewHolder>(TVShowComparator()) {
}