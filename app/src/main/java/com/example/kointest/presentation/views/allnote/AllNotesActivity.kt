package com.example.kointest.presentation.views.allnote

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kointest.R
import com.example.kointest.domain.entity.Note
import com.example.kointest.domain.viewmodel.NoteViewModel
import com.example.kointest.presentation.adapter.NoteAdapter
import com.example.kointest.presentation.views.newnote.AddNewNoteActivity
import kotlinx.android.synthetic.main.activity_all_notes.*
import org.koin.android.ext.android.inject

class AllNotesActivity : AppCompatActivity(), AllNotesView {


    private val mAdapter : NoteAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_notes)

        val mViewModelProvider = ViewModelProviders.of(this@AllNotesActivity).get(
            NoteViewModel(
                application
            )::class.java)

        configRecycler()

        mViewModelProvider.getAllNotes().observe(this, Observer<List<Note>> {
                mAdapter.setNote(it!!)

        })

        btn_new_note.setOnClickListener {
            startActivity(Intent(this, AddNewNoteActivity::class.java ))
        }

    }

    override fun configRecycler() {
        recycler_all_notes.setHasFixedSize(true)
        recycler_all_notes.layoutManager =
            LinearLayoutManager(
                this,
                RecyclerView.VERTICAL,
                false
            )
        recycler_all_notes.adapter = mAdapter
    }



}
