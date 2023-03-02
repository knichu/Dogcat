package com.godminq.dogcat.ui.today

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.godminq.dogcat.R
import com.godminq.dogcat.adapters.*
import com.godminq.dogcat.databinding.FragmentTodayViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodayViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentTodayViewPagerBinding

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

        // fab
        binding.fab.setOnClickListener {
            when (binding.todayViewPager.currentItem) {
                0 -> {


                    Log.d("태그", "check1")
                    Toast.makeText(context, "check1", Toast.LENGTH_SHORT).show()
                }
                1 -> {


                    Log.d("태그", "check2")
                    Toast.makeText(context, "check2", Toast.LENGTH_SHORT).show()
                }
            }
        }
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