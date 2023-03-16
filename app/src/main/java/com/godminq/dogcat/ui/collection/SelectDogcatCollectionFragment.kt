package com.godminq.dogcat.ui.collection

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
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
            val action1 = SelectDogcatCollectionFragmentDirections.
            actionSelectDogcatCollectionFragmentToDogAndCatCollectionFragment("Dog")
            findNavController().navigate(action1)
        }

        view.findViewById<View>(R.id.constraintLayoutCatcollection).setOnClickListener {
            val action2 = SelectDogcatCollectionFragmentDirections.
            actionSelectDogcatCollectionFragmentToDogAndCatCollectionFragment("Cat")
            findNavController().navigate(action2)
        }
        return view
    }



}
