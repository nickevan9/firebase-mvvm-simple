package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

/**
 * Created by nickevan on 10,October,2019
 */
class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewholder>() {

    private var data: List<Note> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewholder {
        return NoteViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: NoteViewholder, position: Int) =
        holder.bind(data[position])

    fun swapData(newData: List<Note>) {
        this.data = newData
    }

    class NoteViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvName: TextView = itemView.findViewById(R.id.tvUserName)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDes: TextView = itemView.findViewById(R.id.tvDescription)

        fun bind(item: Note) = with(itemView) {
            tvName.text = item.username
            tvTitle.text = item.title
            tvDes.text = item.description
            setOnClickListener {

            }
        }
    }
}