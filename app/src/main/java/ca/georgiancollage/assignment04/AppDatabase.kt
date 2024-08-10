package ca.georgiancollage.assignment04

import androidx.room.Database
import androidx.room.RoomDatabase
// This annotation marks the class as a Room Database
@Database(entities = [TASKShow::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase()
{


}