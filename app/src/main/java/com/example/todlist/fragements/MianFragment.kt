package com.example.todlist.fragements



import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todlist.Database.UserViewModel
import com.example.todlist.R
import kotlinx.android.synthetic.main.fragment_mian.view.*


class MianFragment : androidx.fragment.app.Fragment() {
    // TODO: Rename and change types of parameters
    private  lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_mian,container,false)
        //Recyclerview
        val adapter = ListAdapter()
        val recyclerView = view.recycleview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //uUserViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user->
            adapter.setData(user)
        })

       view.floatingbutton.setOnClickListener{
           findNavController().navigate(R.id.action_mianFragment_to_addFragment)
       }

        return view
    }


}