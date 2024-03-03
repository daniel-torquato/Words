package xyz.torquato.words.data

import kotlinx.coroutines.flow.Flow

interface WordsRepository {
    fun getAllItemsStream(): Flow<List<Word>>

    fun getItemStream(id: String): Flow<Word?>

    suspend fun insertWord(word: Word)

    suspend fun deleteWord(word: Word)

    suspend fun updateWord(word: Word)
}