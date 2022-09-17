package com.example.nasaious.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nasaious.data.local.dao.PropertyDao
import com.example.nasaious.data.local.entity.FacilityEntity
import com.example.nasaious.data.local.entity.PropertyEntity

@Database(
        entities = [
            PropertyEntity::class,
            FacilityEntity::class
        ], version = 2, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun propertyDao(): PropertyDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room
                    .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }
}