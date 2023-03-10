package com.godminq.dogcat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.godminq.dogcat.R
import com.godminq.dogcat.adapters.TodayDogTabPagingDataAdapter.DogTabViewHolder
import com.godminq.dogcat.data.entity.TheDogApiSearchResponse
import com.godminq.dogcat.databinding.ListItemTodayDogBinding

class TodayDogTabPagingDataAdapter :
    PagingDataAdapter<TheDogApiSearchResponse, DogTabViewHolder>(
        DogTabDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogTabViewHolder {
        return DogTabViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_today_dog,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DogTabViewHolder, position: Int) {
        val photo = getItem(position)
        if (photo != null) {
            holder.bind(photo)
        }
    }

    class DogTabViewHolder(
        private val binding: ListItemTodayDogBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {

        }

        fun bind(item: TheDogApiSearchResponse) {
            binding.apply {
                response = item
                executePendingBindings()
            }
        }
    }

    fun getItemData(position: Int) = this.getItem(position)

}

private class DogTabDiffCallback : DiffUtil.ItemCallback<TheDogApiSearchResponse>() {
    override fun areItemsTheSame(oldItem: TheDogApiSearchResponse, newItem: TheDogApiSearchResponse)
    : Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: TheDogApiSearchResponse, newItem: TheDogApiSearchResponse)
    : Boolean {
        return oldItem == newItem
    }
}