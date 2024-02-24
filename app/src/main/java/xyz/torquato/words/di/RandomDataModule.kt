package xyz.torquato.words.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import xyz.torquato.words.data.RandomGenerator
import xyz.torquato.words.data.RandomGeneratorImpl

@Module
@InstallIn(SingletonComponent::class)
object RandomDataModule {
    @Provides
    fun providesRandomGenerator(): RandomGenerator = RandomGeneratorImpl()
}