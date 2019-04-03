package com.example.kointest.infra.cache

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kointest.domain.entity.Note
import io.reactivex.Completable

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note : Note) : Completable

    @Query("SELECT * FROM note_table")
    fun getAllNotes() : LiveData<List<Note>>

    @Delete
    fun deleteNote(note: Note) : Completable

    @Update
    fun update(vararg note: Note) : Completable
}