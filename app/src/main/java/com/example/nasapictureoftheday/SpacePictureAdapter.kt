package com.driuft.random_pets_starter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nasapictureoftheday.R

class SpacePictureAdapter(private val spaceList: List<String>) : RecyclerView.Adapter<SpacePictureAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val spaceImage: ImageView
        val explanation: TextView
        val name: TextView
        val date: TextView

        init {
            // Find our RecyclerView item's ImageView for future use
            spaceImage = view.findViewById(R.id.spaceimageView)
            explanation = view.findViewById(R.id.description)
            name = view.findViewById(R.id.Name)
            date = view.findViewById(R.id.date)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.picture_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return spaceList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val spacePicture = spaceList[position]
        Glide.with(holder.itemView)
            .load(spaceList[position])
            .centerCrop()
            .into(holder.spaceImage)

        //holder.explanation.text = spacePicture.description
        //holder.name.text = spacePicture.Name
        //holder.date.text = spacePicture.date
    }

}