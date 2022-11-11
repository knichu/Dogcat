package com.godminq.dogcat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.godminq.dogcat.adapters.*
import com.godminq.dogcat.databinding.FragmentMainViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = DogcatPagerAdapter(this)

        // Set the icon and text for each tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

//        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }


    private fun getTabIcon(position: Int): Int {
        return when (position) {
            TODAY_DOGCAT_PAGE_INDEX -> R.drawable.today_dogcat_tab_selector
            DOGCAT_COLLECTION_PAGE_INDEX -> R.drawable.dogcat_collection_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            TODAY_DOGCAT_PAGE_INDEX -> getString(R.string.today_dogcat_tab_title)
            DOGCAT_COLLECTION_PAGE_INDEX -> getString(R.string.dogcat_collection_tab_title)
            else -> null
        }
    }
}