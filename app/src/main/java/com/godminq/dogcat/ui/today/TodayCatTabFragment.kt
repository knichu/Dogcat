package com.godminq.dogcat.ui.today

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.godminq.dogcat.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodayCatTabFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_today_cat_tab, container, false)
    }

}