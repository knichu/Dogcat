package com.godminq.dogcat.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.godminq.dogcat.R
import com.godminq.dogcat.data.entity.DogAndCat
import com.godminq.dogcat.databinding.ListItemImageBinding
import com.godminq.dogcat.ui.collection.DogAndCatCollectionFragmentDirections
import com.godminq.dogcat.viewmodels.CollectionAdapterViewModel

class DogAndCatCollectionAdapter :
    ListAdapter<DogAndCat, DogAndCatCollectionAdapter.ViewHolder>(
        GardenPlantDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_image,
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
        private val binding: ListItemImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { view ->
                binding.viewModel?.animalId?.let {animalName ->
                    navigateToDetail(animalName, view)
                }
            }
        }

        private fun navigateToDetail(animalName: String, view: View) {
            val direction = DogAndCatCollectionFragmentDirections
                .actionDogAndCatCollectionFragmentToDetailDogAndCatCollectionFragment(animalName)
            view.findNavController().navigate(direction)
        }

        fun bind(dogAndCat: DogAndCat) {
            with(binding) {
                viewModel = CollectionAdapterViewModel(dogAndCat)
                executePendingBindings()
            }
        }
    }
}

private class GardenPlantDiffCallback : DiffUtil.ItemCallback<DogAndCat>() {

    override fun areItemsTheSame(
        oldItem: DogAndCat,
        newItem: DogAndCat
    ): Boolean {
        return oldItem.plant.plantId == newItem.plant.plantId
    }

    override fun areContentsTheSame(
        oldItem: DogAndCat,
        newItem: DogAndCat
    ): Boolean {
        return oldItem.plant == newItem.plant
    }
}