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
import com.godminq.dogcat.data.entity.Cat
import com.godminq.dogcat.data.entity.Dog
import com.godminq.dogcat.data.repo.CatRepository
import com.godminq.dogcat.data.repo.DogRepository
import com.godminq.dogcat.databinding.FragmentDogAndCatCollectionBinding
import com.godminq.dogcat.databinding.ListItemCatCollectionBinding
import com.godminq.dogcat.viewmodels.CollectionCatAdapterViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CollectionCatAdapter @Inject constructor(
    private val catRepository: CatRepository,
    private val fragmentBinding: FragmentDogAndCatCollectionBinding
    ) : ListAdapter<Cat, CollectionCatAdapter.ViewHolder>(
        LikedCatDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_cat_collection,
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
        private val binding: ListItemCatCollectionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val deleteImageButton: ImageButton = binding.deleteCatImageButton
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

    private fun deleteItem(cat: Cat, position: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            catRepository.deleteCat(cat)
//            notifyItemRemoved(position)
            notifyDataSetChanged()
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