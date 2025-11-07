package com.example.splitsmart20

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MemberAdapter(
    private val members: List<MemberData>
) : RecyclerView.Adapter<MemberAdapter.ViewHolder>() {

    // ViewHolder class
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image_icon)
        val nameText: TextView = view.findViewById(R.id.memberName)
        val amountText: TextView = view.findViewById(R.id.amountOwed)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_member, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val member = members[position]
        holder.image.setImageResource(R.drawable.ic_person)
        holder.nameText.text = member.name
        holder.amountText.text = "R%.2f".format(member.eachShare)
    }

    override fun getItemCount(): Int = members.size
}

