package com.hdsw.asimpleapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cats")
data class Cat(
    @PrimaryKey
    @SerializedName("_id")
    val id: String,
    val createdAt: String,
    val owner: String,
    val tags: List<String>,
    val updatedAt: String
)