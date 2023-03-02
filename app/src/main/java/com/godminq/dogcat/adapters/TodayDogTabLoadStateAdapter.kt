package com.godminq.dogcat.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.godminq.dogcat.R
import com.godminq.dogcat.databinding.LayoutLoadingStateBinding

class TodayDogTabLoadStateAdapter (
    private val retry: () -> Unit
) : LoadStateAdapter<TodayDogTabLoadStateAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_loading_state,
                parent,
                false
            ),
            retry
        )
    }

    class ViewHolder(
        private val binding: LayoutLoadingStateBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            with(binding) {
                retryButton.setOnClickListener { retry() }
                isLoading = loadState is LoadState.Loading
                isError = loadState is LoadState.Error
//                errorMessage = (loadState as? LoadState.Error)?.error?.message ?: ""
            }
        }
    }
}