 package com.example.todlist.fragements

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todlist.Database.User
import com.example.todlist.Database.UserViewModel
import com.example.todlist.R

import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import java.util.*
import kotlin.properties.Delegates


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class UpdateFragment : Fragment() ,DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{


    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel
    private var statu by Delegates.notNull<Boolean>()

    var date:Date = Calendar.getInstance().getTime()
    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0
    var savedHour = 0
    var savedMinute = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

//        dateupdate.text = Calendar.getInstance().toString()

        view.updateeditTextTexttitle.setText(args.currentUser.title)
        view.updateeditTextdescription.setText(args.currentUser.description)
   //     view.dateupdate.setdate(args.currentUser.date)


            view.status.setChecked(args.currentUser.statu)
              if (args.currentUser.statu){
                  view.status_text.setText("this task is done!")
              }
        view.dateupdate.setOnClickListener {
            pickdate()
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

        //Add menu
        setHasOptionsMenu(true)



        //getActivity()?.setTitle("args.currentUser.title");


        return view
    }


    private fun  updateItem() {
        val title = updateeditTextTexttitle.text.toString()
        val description = updateeditTextdescription.text.toString()
        val date = Date(savedYear,savedMonth,savedDay,savedHour,savedMinute)

        if(inputCheck(title, description, statu,date)){
            // creare user object

            val updateUser= User(args.currentUser.id,title,description,statu,date)
            // add data to database
            mUserViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(),"successfully updated!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment3_to_mianFragment)
        }else{
            Toast.makeText(requireContext(),"please fill out all fields", Toast.LENGTH_LONG).show()
        }



    }
    private fun inputCheck(
        title: String,
        description: String,
        status: Boolean,
        date:Date
    ): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))

    }


    private fun getDateTimecalculator() {

        val cal: Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
    }
    private fun pickdate() {
        getDateTimecalculator()
        DatePickerDialog(requireContext(), this, year, month, day).show()
    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMounth: Int) {
        savedDay = dayOfMounth
        savedMonth = month
        savedYear = year
        val today: Calendar = Calendar.getInstance()
        getDateTimecalculator()

        TimePickerDialog(requireContext(), this, hour, minute, true).show()



    }

    override fun onTimeSet(view: TimePicker?, hourofday: Int, minute: Int) {
        savedHour = hourofday
        savedMinute = minute

        dateupdate.text = "$savedDay-$savedMonth-$savedYear Hr$savedHour:$savedMinute"
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete)
            deleteUser()
        return super.onOptionsItemSelected(item)
    }
    private fun deleteUser(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes"){_, _->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(),"Successfully removed: ${args.currentUser.title}",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment3_to_mianFragment)
        }
        builder.setNegativeButton("no"){_, _->}
        builder.setTitle("Delete ${args.currentUser.title}?")
        builder.setMessage("Are you Sure You Want to delete ${args.currentUser.title}?")
        builder.create().show()
    }
}


 private operator fun TextView.invoke(date: Date?) {

 }




















