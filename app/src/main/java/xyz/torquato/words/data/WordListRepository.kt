package xyz.torquato.words.data

import android.util.Log
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WordListRepository @Inject constructor(private val wordDao: WordDao): WordsRepository {
    override fun getAllItemsStream(): Flow<List<Word>> = wordDao.getAllWords()

    override fun getItemStream(id: String): Flow<Word?> = wordDao.getWord(id)

    override suspend fun insertWord(word: Word) {
        Log.d("MyTag", "Inserting $word")
        wordDao.insert(word)
    }

    override suspend fun deleteWord(word: Word) = wordDao.delete(word)

    override suspend fun updateWord(word: Word) = wordDao.update(word)
}