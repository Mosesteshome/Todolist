package com.example.todlist.fragements

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todlist.Database.User
import com.example.todlist.Database.UserViewModel
import com.example.todlist.R
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_update.*
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener  {
    // TODO: Rename and change types of parameters
    private lateinit var mUserViewModel:UserViewModel
    private var statu:Boolean=false
    private var day = 0
    private var month = 0
    private var year = 0
    private var hour = 0
    private var minute = 0

    private var savedDay = 0
    private var savedMonth = 0
    private var savedYear = 0
    private var savedHour = 0
    private var savedMinute = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_add, container, false)

         mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.buttonset.setOnClickListener {
            pickdate()
        }

        view.buttonadd.setOnClickListener {

           insertDataToDatabase()


        }


        return view
    }

    private fun insertDataToDatabase() {
        val title = editTextTexttitle.text.toString()
        val description = editTextdescription.text.toString()

        if(inputCheck(title, description,statu)){
            // creare user object
            val user= User(0, title, description, statu)
            // add data to database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(),"successfully added!",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_mianFragment)
        }else{
            Toast.makeText(requireContext(),"please fill out all fields",Toast.LENGTH_LONG).show()
        }
 }
    private fun inputCheck(title: String, description: String, statu: Boolean): Boolean {
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

        setdate.text = "$savedDay-$savedMonth-$savedYear$savedHour  $savedMinute"
    }


}