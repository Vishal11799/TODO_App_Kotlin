package ca.georgiancollage.assignment04

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "task_name")

//model class
data class TASKShow(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val isCompleted: Boolean,
    val dueDate: String,
    val dueTime: String,
    val taskType: String
)
