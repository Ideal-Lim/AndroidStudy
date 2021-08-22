package com.example.todolistapp.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var description: String = "",
    var date:String = ""
)



