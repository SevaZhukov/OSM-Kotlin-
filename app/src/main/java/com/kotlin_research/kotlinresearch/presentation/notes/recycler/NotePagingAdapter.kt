package com.kotlin_research.kotlinresearch.presentation.notes.recycler

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.kotlin_research.kotlinresearch.R
import com.kotlin_research.kotlinresearch.data.room.Note
import com.kotlin_research.kotlinresearch.domain.format.FormatNote
import com.kotlin_research.kotlinresearch.presentation.result_note.ResultNoteController

class NotePagingAdapter(diffCallback: DiffUtil.ItemCallback<Note>, private val router: Router) : PagedListAdapter<Note, NoteViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(v)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        val formatNote = FormatNote.format(note!!)
        holder.itemView.setOnClickListener({
            router.replaceTopController(RouterTransaction.with(ResultNoteController(note)))
        })
        holder.points.text = "${note!!.points}"
        holder.date.text = formatNote.date
        holder.zone.text = formatNote.zone
        holder.zone.setTextColor(formatNote.colorZone)
        holder.moment.setImageResource(formatNote.moment)
    }
}