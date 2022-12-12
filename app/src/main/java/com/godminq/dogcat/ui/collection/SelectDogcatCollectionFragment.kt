package com.godminq.dogcat.ui.collection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.godminq.dogcat.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectDogcatCollectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_select_dogcat_collection, container, false)

        view.findViewById<View>(R.id.constraintLayoutDogcollection).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_dogcatCollectionFragment_to_dogAndCatCollectionFragment)
        }
        view.findViewById<View>(R.id.constraintLayoutCatcollection).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_dogcatCollectionFragment_to_dogAndCatCollectionFragment)
        }

        return view
    }



}
