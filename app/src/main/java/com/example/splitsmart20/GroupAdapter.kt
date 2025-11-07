package com.example.splitsmart20

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GroupAdapter(
    private val groups: List<GroupData>
): RecyclerView.Adapter<GroupAdapter.ViewHolder>() {

    // ViewHolder class
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image_icon)
        val groupNameText: TextView = view.findViewById(R.id.groupName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_group, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val group = groups[position]
        holder.image.setImageResource(R.drawable.ic_group_icon)
        holder.groupNameText.text = group.groupName
    }

    override fun getItemCount(): Int = groups.size
}