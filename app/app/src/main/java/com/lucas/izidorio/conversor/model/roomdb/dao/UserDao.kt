package com.lucas.izidorio.conversor.model.roomdb.dao

import androidx.room.*
import com.lucas.izidorio.conversor.model.roomdb.entities.User

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM users WHERE password = :password AND (username = :user OR email = :user)")
    fun getUser(user: String, password: String): User

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUser(id: Int): User
}