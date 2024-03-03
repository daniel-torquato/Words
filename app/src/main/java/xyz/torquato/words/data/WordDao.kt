package xyz.torquato.words.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Update
    suspend fun update(word: Word)

    @Delete
    suspend fun delete(word: Word)

    @Query("SELECT * from words WHERE word = :word")
    fun getWord(word: String): Flow<Word>

    @Query("SELECT * from words ORDER BY word ASC")
    fun getAllWords(): Flow<List<Word>>


}