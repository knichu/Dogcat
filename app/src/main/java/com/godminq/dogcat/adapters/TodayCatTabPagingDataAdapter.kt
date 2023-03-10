package com.godminq.dogcat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.godminq.dogcat.R
import com.godminq.dogcat.adapters.TodayCatTabPagingDataAdapter.CatTabViewHolder
import com.godminq.dogcat.data.entity.TheCatApiSearchResponse
import com.godminq.dogcat.databinding.ListItemTodayCatBinding

class TodayCatTabPagingDataAdapter:
    PagingDataAdapter<TheCatApiSearchResponse, CatTabViewHolder>(
        CatTabDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatTabViewHolder {
        return CatTabViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_today_cat,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CatTabViewHolder, position: Int) {
        val photo = getItem(position)
        if (photo != null) {
            holder.bind(photo)
        }
    }

    class CatTabViewHolder(
        private val binding: ListItemTodayCatBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {

        }

        fun bind(item: TheCatApiSearchResponse) {
            binding.apply {
                response = item
                executePendingBindings()
            }
        }
    }

    fun getItemData(position: Int) = this.getItem(position)

}

private class CatTabDiffCallback : DiffUtil.ItemCallback<TheCatApiSearchResponse>() {
    override fun areItemsTheSame(oldItem: TheCatApiSearchResponse, newItem: TheCatApiSearchResponse)
            : Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: TheCatApiSearchResponse, newItem: TheCatApiSearchResponse)
            : Boolean {
        return oldItem == newItem
    }
}