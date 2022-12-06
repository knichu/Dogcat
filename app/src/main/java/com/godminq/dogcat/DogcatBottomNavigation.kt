package com.godminq.dogcat

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class DogcatBottomNavigation : BottomNavigationView {

    private var navController: NavController? = null

    private var isImageLoaded = false


    constructor(context: Context) : super(context)


    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)


    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun loadImage(
        imageUrl: String,
        @IdRes itemId: Int,
        @DrawableRes placeHolderResourceId: Int
    ) {
        val navigationItemView = findViewById<BottomNavigationItemView>(itemId)

        val iconContainer =
            navigationItemView.children.find { it is FrameLayout } as? FrameLayout ?: return
        val imageView =
            iconContainer.children.find { it is AppCompatImageView } as? AppCompatImageView
                ?: return

    }


    fun setupWithNavController(navController: NavController) {

        this.navController = navController

        NavigationUI.setupWithNavController(this, navController)

    }
}



