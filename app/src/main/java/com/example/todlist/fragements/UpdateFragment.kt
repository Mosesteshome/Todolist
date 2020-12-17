 package com.example.todlist.fragements

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todlist.Database.User
import com.example.todlist.Database.UserViewModel
import com.example.todlist.R
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

 // TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class UpdateFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.updateeditTextTexttitle.setText(args.currentUser.title)
        view.updateeditTextdescription.setText(args.currentUser.description)

        view.updatebuttonadd.setOnClickListener {
            updateItem()

        }


        return view
    }


    private fun  updateItem() {
        val title = updateeditTextTexttitle.text.toString()
        val description = updateeditTextdescription.text.toString()

        if(inputCheck(title, description)){
            // creare user object
            val updateUser= User(args.currentUser.id,title,description)
            // add data to database
            mUserViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(),"successfully updated!", Toast.LENGTH_LONG)
            findNavController().navigate(R.id.action_updateFragment3_to_mianFragment)
        }else{
            Toast.makeText(requireContext(),"please fill out all fields", Toast.LENGTH_LONG)
        }



    }
    private fun inputCheck(title:String,description:String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))

    }


}