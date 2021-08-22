package com.example.todolistapp.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoEntity::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun TodoDAO() : TodoDAO

    // 객체를 생성하는 비용을 줄이기 위해 싱글톤 패턴을 사용한다.
    companion object{
        var instance : TodoDatabase? = null

        fun getInstance(context: Context): TodoDatabase? {
            if (instance == null){
                synchronized(TodoDatabase::class){
                    instance = Room.databaseBuilder(context.applicationContext,
                        TodoDatabase::class.java, "TodoDB.db")
                        .fallbackToDestructiveMigration() // database 에서 스키마의 변화가 생길 때 모든 데이터를 drop 하고 새로 생성
                        .build()
                }
            }
            return instance
        }
    }
}