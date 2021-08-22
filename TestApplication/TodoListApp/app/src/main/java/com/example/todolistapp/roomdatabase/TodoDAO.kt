package com.example.todolistapp.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface TodoDAO {

    @Insert(onConflict = REPLACE)
    fun insert(todo: TodoEntity)

    @Query("SELECT * FROM todo WHERE DATE = :date")
    fun getAllByDate(date: String): LiveData<List<TodoEntity>?>

    @Delete
    fun delete(todo: TodoEntity)

    @Query("SELECT * FROM todo")
    fun getAll(): LiveData<List<TodoEntity>>
}