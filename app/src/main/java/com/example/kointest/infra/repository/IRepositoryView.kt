package com.example.kointest.infra.repository

import androidx.lifecycle.LiveData
import com.example.kointest.domain.entity.Note
import io.reactivex.Completable

interface IRepositoryView {

    fun getAll()
    fun getAllNote() : LiveData<List<Note>>
    fun insert(note: Note) : Completable
    fun deleteNote(note: Note) : Completable
    fun update(note: Note) : Completable
}