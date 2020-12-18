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
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import kotlin.properties.Delegates

 // TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class UpdateFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel
    private var statu by Delegates.notNull<Boolean>()
    //private var loop:Int=-1

    // private   var check: Boolean=false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.updateeditTextTexttitle.setText(args.currentUser.title)
        view.updateeditTextdescription.setText(args.currentUser.description)


            view.status.setChecked(args.currentUser.statu)
              if (args.currentUser.statu){
                  view.status_text.setText("this task is done!")
              }

        view.updatebuttonadd.setOnClickListener {
            updateItem()

        }
        view.status_button.setOnClickListener {
            findNavController().navigate(R.id.action_updateFragment3_to_statusFragment)
        }
        view.status.setOnClickListener {
            var check: Boolean = status.isChecked()
            //var statu :Boolean=false

            if (check){

                 statu= true
                status_text.setText("this task is done!")
              //  status_text.visibility=View.GONE
                //done_text.visibility = View.VISIBLE

            }else{
                statu=false
                status_text.setText("tick this task if its done!")
                //status_text.visibility=View.VISIBLE
                //done_text.visibility = View.GONE

            }
        }


        //getActivity()?.setTitle("args.currentUser.title");


        return view
    }


    private fun  updateItem() {
        val title = updateeditTextTexttitle.text.toString()
        val description = updateeditTextdescription.text.toString()

        if(inputCheck(title, description, statu)){
            // creare user object

            val updateUser= User(args.currentUser.id,title,description,statu)
            // add data to database
            mUserViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(),"successfully updated!", Toast.LENGTH_LONG)
            findNavController().navigate(R.id.action_updateFragment3_to_mianFragment)
        }else{
            Toast.makeText(requireContext(),"please fill out all fields", Toast.LENGTH_LONG)
        }



    }
    private fun inputCheck(
        title: String,
        description: String,
        status: Boolean
    ): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))

    }


}