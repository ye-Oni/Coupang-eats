package com.minux.template_basic.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "SongTable")
data class Song(
    @SerializedName("title") val title: String,
    @SerializedName("singer") val singer: String,
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @SerializedName("isLike")
    var isLike: Boolean = false
}
