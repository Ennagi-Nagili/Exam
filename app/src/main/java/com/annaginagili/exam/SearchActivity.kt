package com.annaginagili.exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {
    lateinit var recycler: RecyclerView
    lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        recycler = findViewById(R.id.recycler)

        recycler.setHasFixedSize(true)
        recycler.layoutManager = GridLayoutManager(this, 2)

        getMovieByName(intent.getStringExtra("name")!!)
    }

    private fun getMovieByName(text: String) {
        val call = RetrofitClient.getInstance().getApi().getMovieByName(Constants.key, text)
        call.enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                val result: Result? = response.body()
                adapter = Adapter(this@SearchActivity, result!!.results)
                recycler.adapter = adapter
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                Toast.makeText(applicationContext, "An error has occured", Toast.LENGTH_LONG).show();
                Log.e("errorin", t.message.toString())
            }
        })
    }
}