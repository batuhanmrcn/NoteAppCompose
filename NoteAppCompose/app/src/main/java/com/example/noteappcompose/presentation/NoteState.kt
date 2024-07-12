package com.example.noteappcompose.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.noteappcompose.data.Note


data class NoteState(
    //bu sınıf note durumu için.Listeyi en başta boş gösteriyoruz.Title ve descr bir state'de tutuyoruz
    val notes: List<Note> = emptyList(),
    val title: MutableState<String> = mutableStateOf(""),
    val description: MutableState<String> = mutableStateOf("")

)