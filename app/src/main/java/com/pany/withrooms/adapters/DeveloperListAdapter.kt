package com.pany.withrooms.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pany.withrooms.R
import com.pany.withrooms.data.Developer


class DeveloperListAdapter internal constructor(
        context: Context
) : RecyclerView.Adapter<DeveloperListAdapter.DeveloperViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var developers = emptyList<Developer>() // Cached copy

    inner class DeveloperViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val developerItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeveloperViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return DeveloperViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DeveloperViewHolder, position: Int) {
        val current = developers[position]
        holder.developerItemView.text = current.developer
    }

    internal fun setDevelopers(developers: List<Developer>) {
        this.developers = developers
        notifyDataSetChanged()
    }

    override fun getItemCount() = developers.size
}


