package com.godminq.dogcat.ui.collection

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.godminq.dogcat.R
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.godminq.dogcat.adapters.CollectionCatAdapter
import com.godminq.dogcat.adapters.CollectionDogAdapter
import com.godminq.dogcat.data.repo.CatRepository
import com.godminq.dogcat.data.repo.DogRepository
import com.godminq.dogcat.databinding.FragmentDogAndCatCollectionBinding
import com.godminq.dogcat.viewmodels.DogAndCatCollectionViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DogAndCatCollectionFragment : Fragment() {

    private lateinit var binding: FragmentDogAndCatCollectionBinding
    private val args by navArgs<DogAndCatCollectionFragmentArgs>()
    private val viewModel: DogAndCatCollectionViewModel by viewModels()
    private val collectionDogAdapter by lazy { CollectionDogAdapter(viewModel.dogRepo, binding) }
    private val collectionCatAdapter by lazy { CollectionCatAdapter(viewModel.catRepo, binding) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_dog_and_cat_collection,
            container,
        false
        )
        Log.d("태그", "collection0")
        binding.viewModel = viewModel
        Log.d("태그", "collection1")
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("태그", "collection2")
        viewModel.setAnimalType(args.animalTitle)
        Log.d("태그", "collection3")
        val recyclerView = binding.animalCollectionRecyclerView
        Log.d("태그", "test111")
        // recyclerView 에 adapter 연결
        connectAdapter(args.animalTitle, recyclerView)
        Log.d("태그", "test222")

        // init image symbol
        initImageSymbol(args.animalTitle)

        binding.animalCollectionDeleteButton.setOnClickListener {
            when(binding.animalCollectionDeleteButton.text) {
                "Delete" -> {
                    val cancelText = requireContext().resources.getString(R.string.cancel_text)
                    binding.animalCollectionDeleteButton.text = cancelText
                    initAdapterItemsDeleteButtonVisible(recyclerView, args.animalTitle)
                }
                "Cancel" -> {
                    val deleteText = requireContext().resources.getString(R.string.delete_text)
                    binding.animalCollectionDeleteButton.text = deleteText
                    initAdapterItemsDeleteButtonGone(recyclerView, args.animalTitle)
                }
            }
        }

        binding.backButtonAtCollection.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        // Go back to the first Fragment of the Collection tab
//        val navController = findNavController()
//        val stackEntry = navController.currentBackStackEntry
//        if (stackEntry?.destination?.id == R.id.selectDogcatCollectionFragment) {
//            val fragment = stackEntry.savedStateHandle.get<DogAndCatCollectionFragment>("dogAndCatCollectionFragment")
//            if (fragment != null && !fragment.isDetached) {
//                childFragmentManager.beginTransaction().detach(fragment).commit()
//            }
//        }
//    }

    private fun subscribeDogUi(adapter: CollectionDogAdapter, binding: FragmentDogAndCatCollectionBinding) =
        viewModel.dogRepoGetAllDog.observe(viewLifecycleOwner) {
            binding.hasLikedAnimal = it.isNotEmpty()
            adapter.submitList(it) {
                // At this point, the content should be drawn
                activity?.reportFullyDrawn()
            }
        }

    private fun subscribeCatUi(adapter: CollectionCatAdapter, binding: FragmentDogAndCatCollectionBinding) =
        viewModel.catRepoGetAllCat.observe(viewLifecycleOwner) {
            binding.hasLikedAnimal = it.isNotEmpty()
            adapter.submitList(it) {
                // At this point, the content should be drawn
                activity?.reportFullyDrawn()
            }
        }

    private fun connectAdapter(args: String?, recyclerView: RecyclerView) {
        when (args) {
            "Dog" -> {
                recyclerView.adapter = collectionDogAdapter
                subscribeDogUi(collectionDogAdapter, binding)
            }
            "Cat" -> {
                recyclerView.adapter = collectionCatAdapter
                subscribeCatUi(collectionCatAdapter, binding)
            }
        }
    }

    private fun initAdapterItemsDeleteButtonVisible(recyclerView: RecyclerView, args: String?) {
        when (args) {
            "Dog" -> {
                for (i in 0 until (recyclerView.adapter?.itemCount!!)) {
                    val viewHolder = recyclerView.findViewHolderForAdapterPosition(i) as? CollectionDogAdapter.ViewHolder
                    viewHolder?.deleteImageButton?.visibility = View.VISIBLE
                }
            }
            "Cat" -> {
                for (i in 0 until recyclerView.adapter?.itemCount!!) {
                    val viewHolder = recyclerView.findViewHolderForAdapterPosition(i) as? CollectionCatAdapter.ViewHolder
                    viewHolder?.deleteImageButton?.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun initAdapterItemsDeleteButtonGone(recyclerView: RecyclerView, args: String?) {
        when (args) {
            "Dog" -> {
                for (i in 0 until recyclerView.adapter?.itemCount!!) {
                    val viewHolder = recyclerView.findViewHolderForAdapterPosition(i) as? CollectionDogAdapter.ViewHolder
                    viewHolder?.deleteImageButton?.visibility = View.GONE
                }
            }
            "Cat" -> {
                for (i in 0 until recyclerView.adapter?.itemCount!!) {
                    val viewHolder = recyclerView.findViewHolderForAdapterPosition(i) as? CollectionCatAdapter.ViewHolder
                    viewHolder?.deleteImageButton?.visibility = View.GONE
                }
            }
        }
    }

    private fun initImageSymbol(args: String?) {
        when(args) {
            "Dog" -> {
                binding.collectionTitleImageView.setImageResource(R.drawable.dogcat_dog_symbol)
            }
            "Cat" -> {
                binding.collectionTitleImageView.setImageResource(R.drawable.dogcat_cat_symbol)
            }
        }
    }

}


