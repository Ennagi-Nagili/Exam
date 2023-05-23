package com.annaginagili.exam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var recycler: RecyclerView
    lateinit var adapter: Adapter
    lateinit var search: EditText
    lateinit var btn: Button
    lateinit var coming: Button
    lateinit var genres: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler = findViewById(R.id.recycler)
        search = findViewById(R.id.search)
        btn = findViewById(R.id.btn)
        coming = findViewById(R.id.coming)
        genres = findViewById(R.id.genres)

        recycler.setHasFixedSize(true)
        recycler.layoutManager = GridLayoutManager(this, 2)

        getMovies()

        btn.setOnClickListener {
            if (search.text.toString().isNotEmpty()) {
                val intent1 = Intent(this, SearchActivity::class.java)
                intent1.putExtra("name", search.text.toString())
                startActivity(intent1)
            }
        }

        coming.setOnClickListener {
            startActivity(Intent(this, UpComing::class.java))
        }

        getGenres()
    }

    private fun getMovies() {
        val call = RetrofitClient.getInstance().getApi().getMovies(Constants.key)
        call.enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                val result: Result? = response.body()
                adapter = Adapter(this@MainActivity, result!!.results)
                recycler.adapter = adapter
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                Toast.makeText(applicationContext, "An error has occured", Toast.LENGTH_LONG).show();
                Log.e("errorin", t.message.toString())
            }
        })
    }

    private fun getGenres() {
        val call = RetrofitClient.getInstance().getApi().getGenres(Constants.key)
        call.enqueue(object : Callback<Genres> {
            override fun onResponse(call: Call<Genres>, response: Response<Genres>) {
                val genre: Genres? = response.body()
                var text = ""
                for (i in genre!!.genres) {
                    text += i.name + " "
                }
                genres.text = text
            }

            override fun onFailure(call: Call<Genres>, t: Throwable) {
                Toast.makeText(applicationContext, "An error has occured", Toast.LENGTH_LONG).show();
                Log.e("errorin", t.message.toString())
            }
        })
    }
}