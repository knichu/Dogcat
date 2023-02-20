package com.godminq.dogcat.ui.collection

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.godminq.dogcat.R
import com.godminq.dogcat.data.entity.Cat
import com.godminq.dogcat.data.entity.Dog
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.godminq.dogcat.adapters.CollectionCatAdapter
import com.godminq.dogcat.adapters.CollectionDogAdapter
import com.godminq.dogcat.databinding.FragmentDogAndCatCollectionBinding
import com.godminq.dogcat.viewmodels.DogAndCatCollectionViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DogAndCatCollectionFragment : Fragment() {

    private lateinit var binding: FragmentDogAndCatCollectionBinding

    // 지연 호출 문제 없으면 cat도 지연호출 할것
    private val dogAdapter by lazy { CollectionDogAdapter() }
    private val catAdapter = CollectionCatAdapter()

    private val args by navArgs<DogAndCatCollectionFragmentArgs>()
    private var searchJob: Job? = null
    private val viewModel: DogAndCatCollectionViewModel by viewModels()

//    private var _viewDataBinding: FragmentDogAndCatCollectionBinding? = null
//    val viewDataBinding: FragmentDogAndCatCollectionBinding
//        get() = _viewDataBinding
//            ?: throw IllegalStateException("viewDataBinding can not be null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val view = inflater.inflate(R.layout.fragment_dog_and_cat_collection, container, false)
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
        Log.d("태그", "collection2")

        viewModel.setAnimalType(args.animalTitle)
        Log.d("태그", "collection3")


        // adapter 연결
        when (args.animalTitle) {
            "Dog" -> {
                binding.animalCollectionRecyclerView.adapter = dogAdapter
                subscribeDogUi(dogAdapter, binding)
            }
            "Cat" -> {
                binding.animalCollectionRecyclerView.adapter = catAdapter
                subscribeCatUi(catAdapter, binding)
            }
        }
        return binding.root
    }

    private fun subscribeDogUi(adapter: CollectionDogAdapter, binding: FragmentDogAndCatCollectionBinding) =
        viewModel.dog.observe(viewLifecycleOwner) {
            binding.hasLikedAnimal = it.isNotEmpty()
            adapter.submitList(it) {
                // At this point, the content should be drawn
                activity?.reportFullyDrawn()
            }
        }

    private fun subscribeCatUi(adapter: CollectionCatAdapter, binding: FragmentDogAndCatCollectionBinding) =
        viewModel.cat.observe(viewLifecycleOwner) {
            binding.hasLikedAnimal = it.isNotEmpty()
            adapter.submitList(it) {
                // At this point, the content should be drawn
                activity?.reportFullyDrawn()
            }
        }
}


