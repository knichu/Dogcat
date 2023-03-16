package com.godminq.dogcat.ui.today

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import com.godminq.dogcat.R
import com.godminq.dogcat.adapters.*
import com.godminq.dogcat.databinding.FragmentTodayViewPagerBinding
import com.godminq.dogcat.viewmodels.TodayViewPagerViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodayViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentTodayViewPagerBinding
    private val viewModel: TodayViewPagerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_today_view_pager,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = binding.todayTabs
        val viewPager = binding.todayViewPager

        // init viewPager
        viewPager.adapter = TodayDogcatPagerAdapter(this)

        // Set the icon and text for each tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            TODAY_DOG_TAB_PAGE_INDEX -> R.drawable.dog_footprint
            TODAY_CAT_TAB_PAGE_INDEX -> R.drawable.cat_footprint
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            TODAY_DOG_TAB_PAGE_INDEX -> getString(R.string.today_dogcat_dog_tab_title)
            TODAY_CAT_TAB_PAGE_INDEX -> getString(R.string.today_dogcat_cat_tab_title)
            else -> null
        }
    }
}