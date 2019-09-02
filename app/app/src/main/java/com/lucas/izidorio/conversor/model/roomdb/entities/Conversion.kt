package com.lucas.izidorio.conversor.model.roomdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.lucas.izidorio.conversor.model.retrofit.serialization.Conversion


@Entity(tableName = "conversions")
data class Conversion(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "info") var info: String
) {
    constructor():this(0, "")
    fun format(): Conversion {
        return Gson().fromJson(info, Conversion::class.java)
    }
}