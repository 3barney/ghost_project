package com.jomo.gohst.di.modules

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.jomo.gohst.data.source.Database
import com.jomo.gohst.data.source.GhostDao
import com.jomo.gohst.ui.GhostViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideGhostDatabase(app: Application): Database =
        Room.databaseBuilder(
            app,
            Database::class.java,
            "ghost_db"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()


    @Provides
    @Singleton
    fun provideGhostDao(database: Database): GhostDao = database.ghostDao()

    @Provides
    fun provideGhostViewModelFactory(factory: GhostViewModelFactory):
            ViewModelProvider.Factory = factory
}