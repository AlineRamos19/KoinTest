package com.example.kointest.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.kointest.R
import com.example.kointest.domain.entity.Note
import com.example.kointest.domain.utils.Priority
import kotlinx.android.synthetic.main.row_note_item.view.*


class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    var mListNote = listOf<Note>()
        set(value) {
            if (field != value) {
                field = value
                notifyDataSetChanged()
            }
        }

    var listener : ((Note) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_note_item, parent, false)
        return NoteHolder(view)
    }

    override fun getItemCount() = mListNote.size

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
       val itemNote = mListNote[position]
        holder.bindView(itemNote, listener)
    }


    class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val priority : View = itemView.priority
        val context = itemView.context

        fun bindView(item : Note, listener : ((Note) -> Unit)?) = with(itemView){
            title.text = item.titleNote
            body.text = item.contentNote
            date.text = item.dateNote

            setColorPriority(item.priorityNote)

            listener?.let { setOnClickListener { it(item) } }

        }

        private fun setColorPriority(priorityNote: Int) {
            when(priorityNote){
                Priority.LOW.ordinal ->
                    priority.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_light))

                Priority.MEDIUM.ordinal ->
                    priority.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_orange_light))

                Priority.HIGH.ordinal ->
                    priority.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
            }
        }

    }

}

