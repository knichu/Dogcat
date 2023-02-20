package com.godminq.dogcat.ui.today

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.godminq.dogcat.R
import com.godminq.dogcat.databinding.FragmentTodayDogTabBinding

class TodayDogTabFragment : Fragment() {

    private lateinit var binding: FragmentTodayDogTabBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        binding = DataBindingUtil.inflate(
//            inflater,
//            R.layout.fragment_dog_and_cat_collection,
//            container,
//            false
//        )
//        return binding.root

        return inflater.inflate(R.layout.fragment_today_cat_tab, container, false)
    }

}