package com.meruj.picturesandweather.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.meruj.picturesandweather.R
import com.meruj.picturesandweather.data.model.Picture

class PicturesAdapter :
    RecyclerView.Adapter<PicturesAdapter.ViewHolder>() {
    private var pictures: List<Picture> = ArrayList()
    private val requestOption: RequestOptions = RequestOptions().error(R.drawable.ic_launcher_background)

    fun setRecyclerData(data: List<Picture>) {
        pictures = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.picture_item, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.author.text = pictures[position].author
        Glide.with(holder.itemView.context).setDefaultRequestOptions(requestOption)
            .load(pictures.get(position).download_url)
            .into(holder.picture)
    }

    override fun getItemCount(): Int = pictures.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var author: TextView = view.findViewById(R.id.author)
        var picture: ImageView = view.findViewById(R.id.picture)
    }
}