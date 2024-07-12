package com.example.noteappcompose.presentation

import com.example.noteappcompose.data.Note


sealed interface NotesEvent {
    object SortNotes: NotesEvent
     //Notları silmek kaydetmek ve sıralamak için data class'lar oluşturdum.
    data class DeleteNote(val note: Note): NotesEvent

    data class SaveNote(
        val title: String,
        val description: String
    ): NotesEvent
}