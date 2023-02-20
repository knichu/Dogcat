package com.godminq.dogcat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.godminq.dogcat.R
import com.godminq.dogcat.data.entity.Dog
import com.godminq.dogcat.databinding.ListItemDogImageBinding
import com.godminq.dogcat.viewmodels.CollectionDogAdapterViewModel

class CollectionDogAdapter :
    ListAdapter<Dog, CollectionDogAdapter.ViewHolder>(
        LikedDogDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_dog_image,
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
        private val binding: ListItemDogImageBinding
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

        fun bind(dog: Dog) {
            with(binding) {
                viewModel = CollectionDogAdapterViewModel(dog)
                executePendingBindings()
            }
        }

    }
}

private class LikedDogDiffCallback : DiffUtil.ItemCallback<Dog>() {

    override fun areItemsTheSame(
        oldItem: Dog,
        newItem: Dog
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Dog,
        newItem: Dog
    ): Boolean {
        return oldItem.id == newItem.id
    }
}