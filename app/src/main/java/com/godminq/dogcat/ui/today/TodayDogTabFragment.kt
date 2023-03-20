package com.godminq.dogcat.ui.today

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.godminq.dogcat.R
import com.godminq.dogcat.adapters.TodayDogTabPagingDataAdapter
import com.godminq.dogcat.adapters.TodayTabLoadStateAdapter
import com.godminq.dogcat.databinding.FragmentTodayDogTabBinding
import com.godminq.dogcat.viewmodels.TodayDogTabViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
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

        val recyclerView = binding.dogImageList

        // init recyclerView
        recyclerView.adapter = adapter.withLoadStateFooter(
            footer = TodayTabLoadStateAdapter { adapter.retry() }
        )

        // activate one swipe, one move
        viewModel.snap.attachToRecyclerView(recyclerView)

        // api loading
        if (adapter.itemCount < 10) {
            searchDog(10)
        }

        // save image
        binding.dogFab.setOnClickListener {
            addImageToCollection()
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

    // fab 버튼이 눌렸을 때
    private fun addImageToCollection() {
        lifecycleScope.launch {
            val savingData = adapter.getItemData(viewModel.getCurrentItemPosition(binding.dogImageList))
            Log.d("태그", "data1 = $savingData")
            if (savingData != null) {
                val numOfDogId = viewModel.checkNumOfDogId(savingData.id).take(1)
                Log.d("태그", "data2 = $numOfDogId")
                var flag = true
                numOfDogId.collect{
                    Log.d("태그", "data3 = $it")
                    if (it == 0) {
                        viewModel.addImageDataToCollection(savingData)
                        Toast.makeText(context, "Image Saved", Toast.LENGTH_SHORT).show()
                        Log.d("태그", "data5 = $it")
                        flag = false
                    } else {
                        if (flag) {
                            Toast.makeText(context, "Image Already Saved", Toast.LENGTH_SHORT).show()
                            Log.d("태그", "data6 = $it")
                            flag = false
                        } else {
                            flag = false
                        }
                    }
                }
            }
        }
    }

}