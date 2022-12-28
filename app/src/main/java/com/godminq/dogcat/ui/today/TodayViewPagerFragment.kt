package com.godminq.dogcat.ui.today

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.godminq.dogcat.R
import com.godminq.dogcat.adapters.*
import com.godminq.dogcat.databinding.FragmentTodayViewPagerBinding
import com.godminq.dogcat.viewmodels.TodayViewPagerViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodayViewPagerFragment : Fragment() {

//    private val todayViewPagerViewModel :TodayViewPagerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTodayViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.todayTabs
        val viewPager = binding.todayViewPager

        viewPager.adapter = TodayDogcatPagerAdapter(this)

        // Set the icon and text for each tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

//        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    // 각 selector 부분 새로 만들어야함
    private fun getTabIcon(position: Int): Int {
        return when (position) {
            TODAY_DOG_TAB_PAGE_INDEX -> R.drawable.today_dogcat_tab_selector
            TODAY_CAT_TAB_PAGE_INDEX -> R.drawable.dogcat_collection_tab_selector
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