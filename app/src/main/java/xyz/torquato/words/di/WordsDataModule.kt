package xyz.torquato.words.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import xyz.torquato.words.data.WordDao
import xyz.torquato.words.data.WordListRepository
import xyz.torquato.words.data.WordsDatabase
import xyz.torquato.words.data.WordsRepository

@Module
@InstallIn(SingletonComponent::class)
object WordsDataModule {

    @Provides
    fun providesWordsDatabase(@ApplicationContext appContext: Context): WordsDatabase {
        return Room.databaseBuilder(appContext, WordsDatabase::class.java, "app.db")
            .build()
    }

    @Provides
    fun providesWordDao(database: WordsDatabase): WordDao {
        return database.wordDao()
    }

    @Provides
    fun providesWordsRepository(wordDao: WordDao): WordsRepository = WordListRepository(wordDao)
}