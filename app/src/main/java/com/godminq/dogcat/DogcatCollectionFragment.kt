package com.godminq.dogcat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.godminq.dogcat.databinding.FragmentTodayDogcatBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DogcatCollectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dogcat_collection, container, false)

        view.findViewById<View>(R.id.constraintLayoutDogcollection).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_dogcatCollectionFragment_to_dogCollectionFragment)
        }
        view.findViewById<View>(R.id.constraintLayoutCatcollection).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_dogcatCollectionFragment_to_catCollectionFragment)
        }

        return view
    }



}
