package com.example.retrofit

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meme_table")
data class Memes(
    var title: String,
    var memeImage: Bitmap
){
  @PrimaryKey(autoGenerate = true) var id = 0
}

