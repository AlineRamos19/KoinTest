package com.example.kointest.infra.repository

import com.example.kointest.domain.entity.Note
import com.example.kointest.infra.cache.NoteDao
import io.reactivex.Completable
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class NoteRepository: KoinComponent, IRepositoryView {


    private val mDao : NoteDao by inject()

    override fun getAllNote()  = mDao.getAllNotes()

    override fun insert(note: Note) : Completable {
        return mDao.insert(note)

    }

    override fun update(note: Note) = mDao.update(note)

    override fun deleteNote(note: Note) = mDao.deleteNote(note)



}