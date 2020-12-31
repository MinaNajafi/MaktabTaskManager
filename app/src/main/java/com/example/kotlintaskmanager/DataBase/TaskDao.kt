package ir.najafi.cafebazaarinterview.DataBase

import androidx.room.*
import com.example.kotlintaskmanager.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTask(task: Task)

    @Query("DELETE FROM task_table")
    suspend fun deleteAllTasks()

    @Query("SELECT * FROM TASK_TABLE ORDER BY title ASC")
    fun getSortedTasks(): Flow<List<Task>>

}

