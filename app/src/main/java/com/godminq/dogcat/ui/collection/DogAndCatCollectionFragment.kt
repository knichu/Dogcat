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
import com.godminq.dogcat.adapters.DogAndCatCollectionAdapter
import com.godminq.dogcat.databinding.FragmentDogAndCatCollectionBinding
import com.godminq.dogcat.viewmodels.DogAndCatCollectionViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DogAndCatCollectionFragment : Fragment() {

    private lateinit var binding: FragmentDogAndCatCollectionBinding
    private val adapter = DogAndCatCollectionAdapter()
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
    ): View {
//        val view = inflater.inflate(R.layout.fragment_dog_and_cat_collection, container, false)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_dog_and_cat_collection,
            container,
        false
        )

        Log.d("태그", "collection0")
//        viewModel = ViewModelProvider(this)[DogAndCatCollectionViewModel::class.java]
        binding.viewModel = viewModel
        Log.d("태그", "collection1")
        binding.lifecycleOwner = this
        Log.d("태그", "collection2")

        viewModel.setAnimalType(args.animalTitle)
        Log.d("태그", "collection3")

        // adapter 연결
        binding.animalCollectionRecyclerView.adapter = adapter
        args.animalTitle?.let { search(it) }

        // db test - test 후 지우기
//        viewModel.getTestDog.observe(viewLifecycleOwner){
//            Log.d("태그", "collection4")
//        }
//        Log.d("태그", "collection5")

//        with(viewDataBinding) {
//
//        }

//        viewModel.setAnimalType(args.animalTitle)
//
//        Log.d("로그", "시작3, ${viewModel.animal.value}")
//
//        with(viewModel) {
//            Log.d("로그", "시작4")
//            animal.observe(viewLifecycleOwner) {
//                Log.d("로그", "시작5, ${it.animalType}")
//
////                viewDataBinding.collectionTitle.text = it.animalType
//
//                Log.d("로그", "시작6")
//            }
//        }

        return binding.root
    }

    private fun search(query: String) {
        // Make sure we cancel the previous job before creating a new one
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchPictures(query).collectLatest {
                adapter.submitData(it)
            }
        }
    }
}


