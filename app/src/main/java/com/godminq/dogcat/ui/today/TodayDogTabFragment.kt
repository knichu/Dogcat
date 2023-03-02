package com.godminq.dogcat.ui.today

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.PagerSnapHelper
import com.godminq.dogcat.R
import com.godminq.dogcat.adapters.TodayDogTabLoadStateAdapter
import com.godminq.dogcat.adapters.TodayDogTabPagingDataAdapter
import com.godminq.dogcat.databinding.FragmentTodayDogTabBinding
import com.godminq.dogcat.viewmodels.DogAndCatCollectionViewModel
import com.godminq.dogcat.viewmodels.TodayDogTabViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TodayDogTabFragment : Fragment() {

    private val adapter = TodayDogTabPagingDataAdapter()
    private lateinit var binding: FragmentTodayDogTabBinding
    private var searchJob: Job? = null
    private val viewModel: TodayDogTabViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_today_dog_tab,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // init recyclerView
        binding.imageList.adapter = adapter.withLoadStateFooter(
            footer = TodayDogTabLoadStateAdapter { adapter.retry() }
        )

        // activate one swipe, one move
        val snap = PagerSnapHelper()
        snap.attachToRecyclerView(binding.imageList)

        // api
        if (adapter.itemCount < 10) {
            searchDog(3)
        }

    }

    private fun searchDog(limit: Int) {
        // Make sure we cancel the previous job before creating a new one
        Log.d("태그", "search dog1")
        searchJob?.cancel()
        Log.d("태그", "search dog2")
        searchJob = lifecycleScope.launch {
            Log.d("태그", "search dog3")
            viewModel.searchDogPictures(limit).collectLatest {
                Log.d("태그", "search dog4")
                adapter.submitData(it)
                Log.d("태그", "search dog5")
            }
        }
    }
}