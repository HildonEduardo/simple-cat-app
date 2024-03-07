package com.hdsw.asimpleapp.data.local.converter

import androidx.room.TypeConverter

class ListStringConverter {

    @TypeConverter
    fun fromListString(list: List<String>?): String? {
        return list?.joinToString(separator = ",")
    }

    @TypeConverter
    fun toListString(value: String?): List<String>? {
        return value?.split(",")?.map { it.trim() }
    }
}
