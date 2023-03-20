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
import com.godminq.dogcat.adapters.TodayCatTabPagingDataAdapter
import com.godminq.dogcat.adapters.TodayDogTabPagingDataAdapter
import com.godminq.dogcat.adapters.TodayTabLoadStateAdapter
import com.godminq.dogcat.databinding.FragmentTodayCatTabBinding
import com.godminq.dogcat.databinding.FragmentTodayDogTabBinding
import com.godminq.dogcat.viewmodels.TodayCatTabViewModel
import com.godminq.dogcat.viewmodels.TodayDogTabViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TodayCatTabFragment : Fragment() {

    private val adapter = TodayCatTabPagingDataAdapter()
    private lateinit var binding: FragmentTodayCatTabBinding
    private var searchJob: Job? = null
    private val viewModel: TodayCatTabViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_today_cat_tab,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.catImageList

        // init recyclerView
        recyclerView.adapter = adapter.withLoadStateFooter(
            footer = TodayTabLoadStateAdapter { adapter.retry() }
        )

        // activate one swipe, one move
        viewModel.snap.attachToRecyclerView(recyclerView)

        // api loading
        if (adapter.itemCount < 10) {
            searchCat(10)
        }

        // save image
        binding.catFab.setOnClickListener {
            addImageToCollection()
        }
    }

    private fun searchCat(limit: Int) {
        // Make sure we cancel the previous job before creating a new one
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchCatPictures(limit).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    // fab 버튼이 눌렸을 때
    private fun addImageToCollection() {
        lifecycleScope.launch {
            val savingData = adapter.getItemData(viewModel.getCurrentItemPosition(binding.catImageList))
            Log.d("태그", "data1 = $savingData")
            if (savingData != null) {
                val numOfCatId = viewModel.checkNumOfCatId(savingData.id).take(1)
                Log.d("태그", "data2 = $numOfCatId")
                var flag = true
                numOfCatId.collect{
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