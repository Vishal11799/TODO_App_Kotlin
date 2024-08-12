package ca.georgiancollage.assignment04

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * AppDatabase is an abstract class that extends RoomDatabase.
 * It serves as the main access point to the SQLite database.
 * This class uses the Room persistence library to manage database creation and version management.
 * The database contains a table for storing tasks (TASKShow entity).
 */
@Database(entities = [TASKShow::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Provides access to the Data Access Object (DAO) for the TASKShow entity.
     * The DAO contains methods used to access the database.
     * @return TASKDao instance for database operations related to TASKShow entity.
     */
    abstract fun taskDao(): TASKDao

    companion object {
        // Volatile annotation ensures that the instance is always up-to-date and thread-safe
        @Volatile
        private var m_instance: AppDatabase? = null

        /**
         * Retrieves the singleton instance of AppDatabase.
         * If the instance is null, a new one is created using Room.databaseBuilder.
         * The synchronized block ensures that only one instance of the database is created at a time.
         * @param context Application context used to build the database.
         * @return Singleton instance of AppDatabase.
         */
        fun getDatabase(context: Context): AppDatabase {
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
