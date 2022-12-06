package com.godminq.dogcat

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.godminq.dogcat.adapters.*
import com.godminq.dogcat.databinding.FragmentMainViewPagerBinding
import com.godminq.dogcat.databinding.FragmentTodayViewPagerBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainViewPagerFragment : Fragment() {

    val binding by lazy { FragmentMainViewPagerBinding.inflate(layoutInflater) }
    private val navController by lazy {
        (parentFragmentManager.findFragmentById(R.id.main_view_pager) as? NavHostFragment)?.navController
    }


    private lateinit var callback: OnBackPressedCallback

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val binding = FragmentMainViewPagerBinding.inflate(inflater, container, false)
        val bottomNavigation = binding.bottomNavigation
        val viewPager = binding.mainViewPager

        // 뷰페이저 스와이프 기능 제거
        binding.mainViewPager.run {
            isUserInputEnabled= false
        }
        // 바텀 네비게이션 설정
        with(binding.bottomNavigation) {
            if (navController != null) setupWithNavController(navController!!)
        }

//        // Set the icon and text for each tab
//        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//            tab.setIcon(getTabIcon(position))
//            tab.text = getTabTitle(position)
//        }.attach()

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