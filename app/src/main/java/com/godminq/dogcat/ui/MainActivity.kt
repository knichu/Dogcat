package com.godminq.dogcat.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.godminq.dogcat.R
import com.godminq.dogcat.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var navController: NavController

    private lateinit var callback: OnBackPressedCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Displaying edge-to-edge
        WindowCompat.setDecorFitsSystemWindows(window, false)

        //setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setContentView(binding.root)

        // 바텀 네비게이션 설정
        val navHostViewPager = supportFragmentManager.findFragmentById(
            R.id.nav_host_container
        ) as NavHostFragment
        navController = navHostViewPager.navController
        // Setup the bottom navigation view with navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setupWithNavController(navController)
    }

//    private fun getTabIcon(position: Int): Int {
//        return when (position) {
//            TODAY_DOGCAT_PAGE_INDEX -> R.drawable.today_dogcat_tab_selector
//            DOGCAT_COLLECTION_PAGE_INDEX -> R.drawable.dogcat_collection_tab_selector
//            else -> throw IndexOutOfBoundsException()
//        }
//    }
//
//    private fun getTabTitle(position: Int): String? {
//        return when (position) {
//            TODAY_DOGCAT_PAGE_INDEX -> getString(R.string.today_dogcat_tab_title)
//            DOGCAT_COLLECTION_PAGE_INDEX -> getString(R.string.dogcat_collection_tab_title)
//            else -> null
//        }
//    }

//    fun replaceFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction().replace(frame.id, fragment).commit()
//    }

}

