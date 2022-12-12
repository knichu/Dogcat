package com.godminq.dogcat.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.godminq.dogcat.ui.today.TodayCatTabFragment
import com.godminq.dogcat.ui.today.TodayDogTabFragment

const val TODAY_DOG_TAB_PAGE_INDEX = 0
const val TODAY_CAT_TAB_PAGE_INDEX = 1

class TodayDogcatPagerAdapter (fragment: Fragment) : FragmentStateAdapter(fragment){

    /**
     * Mapping of the ViewPager page indexes to their respective Fragments
     */
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        TODAY_DOG_TAB_PAGE_INDEX to { TodayDogTabFragment() },
        TODAY_CAT_TAB_PAGE_INDEX to { TodayCatTabFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}
