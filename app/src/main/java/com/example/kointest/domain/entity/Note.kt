package com.example.kointest.domain.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    val titleNote : String,
    val contentNote : String,
    val priorityNote : Int,
    val dateNote : String
) : Serializable








