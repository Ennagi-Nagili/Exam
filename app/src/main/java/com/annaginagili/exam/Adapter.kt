package com.annaginagili.exam

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Adapter(private val context: Context, private val itemList: List<Movie>):
    RecyclerView.Adapter<Adapter.ItemHolder>() {
    class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val image = itemView.findViewById<ImageView>(R.id.image)
        private val title = itemView.findViewById<TextView>(R.id.title)
        private val language = itemView.findViewById<TextView>(R.id.language)
        private val vote = itemView.findViewById<TextView>(R.id.vote)
        private val overview = itemView.findViewById<TextView>(R.id.overview)

        fun setData(item: Movie, context: Context) {
            this.title.text = item.title
            this.language.text = "Language: " + item.original_language
            this.vote.text = "Vote average: " + item.vote_average.toString()
            this.overview.text = item.overview
            Glide.with(context).load(Uri.parse("https://image.tmdb.org/t/p/w500" + item.poster_path)).into(this.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.movie_layout, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(itemList[position], context)
    }
}