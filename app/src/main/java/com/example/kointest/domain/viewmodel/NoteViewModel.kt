package com.example.kointest.domain.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kointest.domain.entity.Note
import com.example.kointest.infra.repository.NoteRepository
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class NoteViewModel constructor(application: Application) : AndroidViewModel(application), KoinComponent{

    private var mListNote : LiveData<List<Note>> = MutableLiveData<List<Note>>()
    private val mRepo : NoteRepository by inject()

    init {
        mListNote.let {
            mListNote = mRepo.getAllNote()
        }
    }

    fun getAllNotes() = mListNote

}
