package com.example.practice.flo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.practice.flo.data.entities.Album
import com.example.practice.flo.data.entities.Like
import com.example.practice.flo.data.entities.Song
import com.example.practice.flo.data.entities.User


@Database(entities = [Song::class, Album::class, User::class, Like::class], version = 1)
abstract class SongDatabase: RoomDatabase() {
    abstract fun songDao(): SongDao
    abstract fun albumDao(): AlbumDao
    abstract fun  userDao(): UserDao


    companion object {
        private var instance: SongDatabase? = null

        @Synchronized
        fun getInstance(context: Context): SongDatabase? {
            if (instance == null) {
                synchronized(SongDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SongDatabase::class.java,
                        "song-database"//다른 데이터 베이스랑 이름이 겹치지 않게
                    ).allowMainThreadQueries().build()
                }
            }

            return instance
        }
    }
}