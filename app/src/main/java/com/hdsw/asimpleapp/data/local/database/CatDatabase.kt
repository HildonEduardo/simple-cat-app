package com.hdsw.asimpleapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hdsw.asimpleapp.data.local.converter.ListStringConverter
import com.hdsw.asimpleapp.data.local.dao.CatDao
import com.hdsw.asimpleapp.data.model.Cat

@Database(entities = [Cat::class], version = 1)
@TypeConverters(ListStringConverter::class)
abstract class CatDatabase : RoomDatabase() {
    abstract fun catDao(): CatDao
}
