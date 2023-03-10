package com.godminq.dogcat.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.godminq.dogcat.R
import com.godminq.dogcat.data.entity.Dog
import com.godminq.dogcat.data.repo.DogRepository
import com.godminq.dogcat.databinding.FragmentDogAndCatCollectionBinding
import com.godminq.dogcat.databinding.ListItemDogCollectionBinding
import com.godminq.dogcat.viewmodels.CollectionDogAdapterViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CollectionDogAdapter @Inject constructor(
    private val dogRepository: DogRepository,
    private val fragmentBinding: FragmentDogAndCatCollectionBinding
    ) : ListAdapter<Dog, CollectionDogAdapter.ViewHolder>(
        LikedDogDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_dog_collection,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val photo = getItem(position)
        if (photo != null) {
            viewHolder.bind(photo)
        }

        viewHolder.deleteImageButton.setOnClickListener {
            deleteItem(photo, position)
        }

        when(fragmentBinding.animalCollectionDeleteButton.text) {
            "Delete" -> {
                viewHolder.deleteImageButton.visibility = View.GONE
            }
            "Cancel" -> {
                viewHolder.deleteImageButton.visibility = View.VISIBLE
            }
        }
    }

    class ViewHolder(
        private val binding: ListItemDogCollectionBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        val deleteImageButton: ImageButton = binding.deleteDogImageButton
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

    private fun deleteItem(dog: Dog, position: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            dogRepository.deleteDog(dog)
//            notifyItemRemoved(position)
            notifyDataSetChanged()
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