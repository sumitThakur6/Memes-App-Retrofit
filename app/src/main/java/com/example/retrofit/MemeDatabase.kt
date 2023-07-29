package com.example.retrofit

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Memes::class], version = 1)
@TypeConverters(Converters::class)
abstract class MemeDatabase : RoomDatabase() {

    abstract fun memeDao(): MemeDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: MemeDatabase? = null

        fun getDatabase(context: Context): MemeDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MemeDatabase::class.java,
                    "meme_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}