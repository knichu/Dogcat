package com.godminq.dogcat.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.godminq.dogcat.DogcatCollectionFragment
import com.godminq.dogcat.TodayDogcatFragment


const val TODAY_DOGCAT_PAGE_INDEX = 0
const val DOGCAT_COLLECTION_PAGE_INDEX = 1

class DogcatPagerAdapter (fragment: Fragment) : FragmentStateAdapter(fragment){

    /**
     * Mapping of the ViewPager page indexes to their respective Fragments
     */
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        TODAY_DOGCAT_PAGE_INDEX to { TodayDogcatFragment() },
        DOGCAT_COLLECTION_PAGE_INDEX to { DogcatCollectionFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}
