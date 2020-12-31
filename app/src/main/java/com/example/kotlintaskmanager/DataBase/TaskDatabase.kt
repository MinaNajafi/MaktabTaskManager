package ir.najafi.cafebazaarinterview.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlintaskmanager.model.Task

@Database(
    entities = [Task::class], //entities = arrayOf(Task::class)
     version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun getTaskDao(): TaskDao
    // Singleton prevents multiple instances of database opening at the
    // same time.
    companion object {
        private const val DB_NAME = "task_database"
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getInstance(context: Context): TaskDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }
            // more kotlinize
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    TaskDatabase::class.java,
//                    DB_NAME
//                ).build()
//                INSTANCE = instance
//                // return instance
//                instance
//            }
        }
    }
}