package com.lucas.izidorio.conversor.model.roomdb.dao

import androidx.room.*
import com.lucas.izidorio.conversor.model.roomdb.entities.Conversion
import com.lucas.izidorio.conversor.model.roomdb.entities.User

@Dao
interface ConversionDao {
    @Insert
    fun insert(conversion: Conversion)

    @Update
    fun update(conversion: Conversion)

    @Delete
    fun delete(conversion: Conversion)

    @Query("SELECT * FROM conversions ORDER BY id DESC")
    fun getAll(): List<Conversion>
}