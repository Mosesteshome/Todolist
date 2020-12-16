package com.example.todlist.fragements


import android.app.Fragment
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.example.todlist.R
import kotlinx.android.synthetic.main.fragment_mian.view.*



class MianFragment : androidx.fragment.app.Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_mian,container,false)

       view.floatingbutton.setOnClickListener{
           findNavController().navigate(R.id.action_mianFragment_to_addFragment)
       }

        return view
    }


}