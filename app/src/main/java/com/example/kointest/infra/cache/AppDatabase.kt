package com.example.kointest.infra.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kointest.domain.entity.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun noteDao() : NoteDao
}