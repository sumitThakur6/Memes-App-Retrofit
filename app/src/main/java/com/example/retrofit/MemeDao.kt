package com.example.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MemeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun insertMeme(meme : Memes)


   @Query("Select * from meme_table")
   fun getMemes() : List<Memes>
}