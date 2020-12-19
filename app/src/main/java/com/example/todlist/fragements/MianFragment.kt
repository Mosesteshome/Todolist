package com.example.todlist.fragements



import android.app.AlertDialog
import android.app.Fragment
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todlist.Api.PostModel
import com.example.todlist.Api.RetrofitInterface
import com.example.todlist.Database.UserViewModel
import com.example.todlist.R
import kotlinx.android.synthetic.main.fragment_mian.view.*
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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
        // Add menu
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId ==R.id.menu_delete){
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun deleteAllUsers(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes"){_, _->
            mUserViewModel.deleteAllUsers()
            Toast.makeText(requireContext(),"Successfully remove everything ",Toast.LENGTH_SHORT).show()

        }
        builder.setNegativeButton("no"){_, _->}
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you Sure You Want to delete All?")
        builder.create().show()
    }
}