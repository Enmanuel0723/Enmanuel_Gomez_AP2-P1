package com.example.enmanuel_gomez_ap2_p1.di

import android.content.Context
import androidx.room.Room
import com.example.enmanuel_gomez_ap2_p1.data.local.database.EntidadDatabase
import com.example.enmanuel_gomez_ap2_p1.data.repository.EntidadRepositoryImpl
import com.example.enmanuel_gomez_ap2_p1.domain.repository.EntidadRepository
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
    fun provideDatabase(@ApplicationContext context: Context): EntidadDatabase {
        return Room.databaseBuilder(
            context,
            EntidadDatabase::class.java,
            "entidad.db"
        ).build()
    }

    @Provides
    fun provideEntidadDao(db: EntidadDatabase) = db.entidadDao()

    @Provides
    @Singleton
    fun provideEntidadRepository(impl: EntidadRepositoryImpl): EntidadRepository = impl
}
