package com.lucas.izidorio.conversor.model.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lucas.izidorio.conversor.model.roomdb.dao.ConversionDao
import com.lucas.izidorio.conversor.model.roomdb.dao.UserDao
import com.lucas.izidorio.conversor.model.roomdb.entities.Conversion
import com.lucas.izidorio.conversor.model.roomdb.entities.User


@Database(entities = [User::class, Conversion::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
    abstract fun conversionDao(): ConversionDao

    companion object {
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context, AppDatabase::class.java, "app.db").build()
    }
}