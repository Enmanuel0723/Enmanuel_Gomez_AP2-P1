package com.example.enmanuel_gomez_ap2_p1.di

import android.content.Context
import androidx.room.Room
import com.example.enmanuel_gomez_ap2_p1.data.local.database.ChequeDatabase
import com.example.enmanuel_gomez_ap2_p1.data.repository.ChequeRepositoryImpl
import com.example.enmanuel_gomez_ap2_p1.domain.repository.ChequeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ChequeDatabase {
        return Room.databaseBuilder(
            context,
            ChequeDatabase::class.java,
            "cheque.db"
        ).build()
    }

    @Provides
    fun provideChequeDao(db: ChequeDatabase) = db.chequeDao()

    @Provides
    @Singleton
    fun provideChequeRepository(impl: ChequeRepositoryImpl): ChequeRepository = impl
}
