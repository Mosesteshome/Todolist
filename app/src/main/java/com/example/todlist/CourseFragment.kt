package com.example.todlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.todlist.Api.PostModel
import com.example.todlist.Api.RetrofitInterface
import kotlinx.android.synthetic.main.fragment_course.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CourseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //retrofit
        var rf: Retrofit = Retrofit.Builder()
            .baseUrl(RetrofitInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        var API: RetrofitInterface = rf.create(RetrofitInterface::class.java)
        var call = API.post
        call?.enqueue(object: Callback<List<PostModel?>?> {
            override fun onFailure(call: Call<List<PostModel?>?>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<List<PostModel?>?>,
                response: Response<List<PostModel?>?>
            ) {
                var postlist : List<PostModel>? = response.body() as List<PostModel>
                var post = arrayOfNulls<String>(postlist!!.size)

                for(i in postlist!!.indices)
                    post[i] = postlist!![i]!!.title
                var adapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_dropdown_item_1line,post)
                listview.adapter = adapter















            }

        })
        return inflater.inflate(R.layout.fragment_course, container, false)
    }
}