package com.godminq.dogcat.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
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

//    private lateinit var callback: OnBackPressedCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Displaying edge-to-edge
        WindowCompat.setDecorFitsSystemWindows(window, false)

        //setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setContentView(binding.root)

        // 뒤로 가기 이벤트 처리 - 방법1
//        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                // 뒤로 가기 버튼을 눌렀을 때의 동작을 여기에 작성합니다.
//                var backPressedTime: Long = 0
//                if (backPressedTime + 2000 > System.currentTimeMillis()) {
//                    onBackPressed()
//                } else {
//                    Toast.makeText(this@MainActivity, "Press once more to exit", Toast.LENGTH_SHORT).show()
//                }
//                backPressedTime = System.currentTimeMillis()
//            }
//        })

        // 바텀 네비게이션 설정
        val navHostViewPager = supportFragmentManager.findFragmentById(
            R.id.nav_host_container
        ) as NavHostFragment
        navController = navHostViewPager.navController
        // Setup the bottom navigation view with navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setupWithNavController(navController)

        // app title clicked, if 부분 재대로 동작 안하는거 같으므로 추후 수정 필요
        binding.appTitle.setOnClickListener {
            navController.popBackStack(navController.graph.startDestinationId, false)
            if (navController.currentDestination?.id != R.id.nav_today_dogcat) {
                navController.navigate(R.id.nav_today_dogcat)
            }
        }
    }

    // 뒤로가기 이벤트
    private var backPressedTime: Long = 0
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.today_view_pager_fragment) {
            if (backPressedTime + 2000 > System.currentTimeMillis()) {
                super.onBackPressed()
            } else {
                Toast.makeText(this, "Press once more to exit", Toast.LENGTH_SHORT).show()
            }
            backPressedTime = System.currentTimeMillis()
        } else {
            super.onBackPressed()
        }
    }

//    // 뒤로 가기 버튼 처리 - 방법2
//    val onBackPressedCallback = object : OnBackPressedCallback(true) {
//        private var doubleBackToExitPressedOnce = false
//
//        override fun handleOnBackPressed() {
//            if (navController.currentDestination?.id == R.id.nav_today_dogcat) {
//                if (doubleBackToExitPressedOnce) {
//                    super.handleOnBackPressed()
//                    return
//                }
//                this.doubleBackToExitPressedOnce = true
//                Toast.makeText(this@MainActivity, "Press once more to exit", Toast.LENGTH_SHORT).show()
//                Handler(Looper.getMainLooper()).postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
//            } else {
//                navController.navigateUp()
//            }
//        }
//    }
//
//    // 뒤로 가기 버튼 콜백 등록
//    this.onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

}

