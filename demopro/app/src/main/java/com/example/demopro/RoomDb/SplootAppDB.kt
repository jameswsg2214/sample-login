package com.example.demopro.RoomDb;

import android.content.Context
import android.provider.SyncStateContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.demopro.RoomDb.Entity.DateConverter
import com.exapmle.demopro.RoomDb.Utils.Constants
import com.work.sploot.DAO.petMasterDao
import com.work.sploot.Entity.other

//This annotation to tell room what is the entity/table of the database
@Database(entities = [other::class], version = 1, exportSchema = true)

@TypeConverters(DateConverter::class)

abstract class SplootAppDB : RoomDatabase() {

    abstract fun petMasterDao(): petMasterDao

    companion object {
        private var INSTANCE: SplootAppDB? = null

        fun getInstance(context: Context): SplootAppDB? {
            if (INSTANCE == null) {
                synchronized(SplootAppDB::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        SplootAppDB::class.java, Constants.DB_NAME)
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}