package com.example.practice.flo.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.practice.flo.data.entities.Album


@Entity(
    tableName = "SongTable",
    foreignKeys = [ForeignKey(
        entity = Album::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("albumIdx"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Song(
    var title: String = "",
    var singer: String = "",
    var second: Int = 0,
    var playTime: Int = 0,
    var isPlaying: Boolean = false,
    var music: String = "",
    var coverImg: Int? = null,
    var isLike: Boolean = false,
    var albumIdx: Int? = null // Foreign key column
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}