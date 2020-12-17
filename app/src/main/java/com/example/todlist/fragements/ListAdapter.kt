package com.example.todlist.fragements

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController

import androidx.recyclerview.widget.RecyclerView
import com.example.todlist.Database.User
import com.example.todlist.R
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter :RecyclerView.Adapter<ListAdapter.MyViewHolder>(){
    
    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))
    }

    override fun getItemCount(): Int {
    return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.title_txt.text = currentItem.title.toString()
        holder.itemView.desc_txt.text = currentItem.description.toString()
        holder.itemView.rowLayout.setOnClickListener{
            val action = MianFragmentDirections.actionMianFragmentToUpdateFragment3(currentItem)
            holder.itemView.findNavController().navigate(action)

        }

    }
    fun setData(user:List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}
























