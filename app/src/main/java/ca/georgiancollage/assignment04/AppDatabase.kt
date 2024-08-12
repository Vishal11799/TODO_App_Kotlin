package ca.georgiancollage.assignment04

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
// This annotation marks the class as a Room Database
@Database(entities = [TASKShow::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase()
{
    abstract fun taskDao(): TASKDao

    companion object
    {
        // Volatile annotation ensures that the instance is always up-to-date and thread-safe
        @Volatile
        private var m_instance: AppDatabase? = null


        fun getDatabase(context: Context): AppDatabase
        {

            return m_instance ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "task_database"
                ).build()

                m_instance = instance


                return instance
            }
        }
    }
}