package com.example.noteappcompose.data

import androidx.room.Entity
import androidx.room.PrimaryKey


//tablo oluşturmak için gibi
@Entity
data class Note(

    //istediğmiz nitelikleri burada yazıyoruz
    val title: String,
    val description: String,
    val dateAdded: Long,

    @PrimaryKey(autoGenerate = true)     //Birincil anahtarı ekliyoruz daha sonrasında otomatik ekliyor id'yi
    val id: Int = 0
)