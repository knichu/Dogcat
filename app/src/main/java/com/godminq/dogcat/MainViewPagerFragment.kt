package com.godminq.dogcat

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.godminq.dogcat.adapters.*
import com.godminq.dogcat.databinding.FragmentMainViewPagerBinding
import com.godminq.dogcat.databinding.FragmentTodayViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainViewPagerFragment : Fragment() {

    val binding by lazy { FragmentMainViewPagerBinding.inflate(layoutInflater) }
    private lateinit var callback: OnBackPressedCallback

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val binding = FragmentMainViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.mainTabs
        val viewPager = binding.mainViewPager

        viewPager.adapter = DogcatPagerAdapter(this)

        // 뷰페이저 스와이프 기능 제거
        binding.mainViewPager.run {
            isUserInputEnabled= false
        }

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


    fun goMainTab() {
        //뷰페이저 내 첫번째 페이지가 아니라면
        if (isNotFirstTab()) {

        }
        //뷰페이저 내 첫번째 페이지가 아니면 뷰페이저 내 에서 뒤로가기
        else binding.mainViewPager.currentItem = binding.mainViewPager.currentItem.minus(1)
    }

    private fun isNotFirstTab() = binding.mainViewPager.currentItem != 0
}