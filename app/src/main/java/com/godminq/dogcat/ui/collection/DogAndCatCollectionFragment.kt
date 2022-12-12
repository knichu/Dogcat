package com.godminq.dogcat.ui.collection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.godminq.dogcat.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DogAndCatCollectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dog_and_cat_collection, container, false)



        return view
    }
}