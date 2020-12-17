package com.example.todlist.fragements

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.example.todlist.Database.User
import com.example.todlist.Database.UserViewModel
import com.example.todlist.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var mUserViewModel:UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_add, container, false)

         mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.buttonadd.setOnClickListener {

           insertDataToDatabase()


        }


        return view
    }

    private fun insertDataToDatabase() {
        val title = editTextTexttitle.text.toString()
        val description = editTextdescription.text.toString()

        if(inputCheck(title, description)){
            // creare user object
            val user= User(0,title,description)
            // add data to database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(),"successfully added!",Toast.LENGTH_LONG)
            findNavController().navigate(R.id.action_addFragment_to_mianFragment)
        }else{
            Toast.makeText(requireContext(),"please fill out all fields",Toast.LENGTH_LONG)
        }



    }
    private fun inputCheck(title:String,description:String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))

    }


}