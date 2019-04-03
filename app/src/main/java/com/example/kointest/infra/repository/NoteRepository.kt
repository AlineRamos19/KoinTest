package com.example.kointest.infra.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kointest.domain.entity.Note
import com.example.kointest.infra.cache.NoteDao
import io.reactivex.Completable
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class NoteRepository: KoinComponent, IRepositoryView {


    //sem usecase, pois esse app de amostra apenas acessa o room e nenhuma API
    private var mListAllNotes : LiveData<List<Note>> = MutableLiveData<List<Note>>()
    private val mDao : NoteDao by inject()

    init {
        getAll()
    }

    override fun getAll() {
       mListAllNotes = mDao.getAllNotes()
    }

    override fun getAllNote() : LiveData<List<Note>>{
        return mListAllNotes
    }

    override fun insert(note: Note) : Completable {
        return mDao.insert(note)

    }

    override fun update(note: Note) : Completable  {
        return mDao.update(note)
    }

    override fun deleteNote(note: Note) : Completable  {
        return mDao.deleteNote(note)
    }

}