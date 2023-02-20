package com.godminq.dogcat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.godminq.dogcat.R
import com.godminq.dogcat.data.entity.Cat
import com.godminq.dogcat.databinding.ListItemCatImageBinding
import com.godminq.dogcat.viewmodels.CollectionCatAdapterViewModel

class CollectionCatAdapter :
    ListAdapter<Cat, CollectionCatAdapter.ViewHolder>(
        LikedCatDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_cat_image,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = getItem(position)
        if (photo != null) {
            holder.bind(photo)
        }
    }

    class ViewHolder(
        private val binding: ListItemCatImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
//            // setClickListener 는 여기에서 설정
//            // 클릭해서 넘어갈때 이부분 활성화
//            binding.setClickListener { view ->
//                binding.viewModel?.animalId?.let {animalName ->
//                    navigateToDetail(animalName, view)
//                }
//            }
        }

//        // 클릭해서 넘어갈때 이부분 활성화
//        private fun navigateToDetail(animalName: String, view: View) {
//            val direction = DogAndCatCollectionFragmentDirections
//                .actionDogAndCatCollectionFragmentToDetailDogAndCatCollectionFragment(animalName)
//            view.findNavController().navigate(direction)
//        }

        fun bind(cat: Cat) {
            with(binding) {
                viewModel = CollectionCatAdapterViewModel(cat)
                executePendingBindings()
            }
        }

    }
}

private class LikedCatDiffCallback : DiffUtil.ItemCallback<Cat>() {

    override fun areItemsTheSame(
        oldItem: Cat,
        newItem: Cat
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Cat,
        newItem: Cat
    ): Boolean {
        return oldItem.id == newItem.id
    }
}