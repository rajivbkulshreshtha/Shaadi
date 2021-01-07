package com.assignment.shaadi.di

import android.content.Context
import androidx.room.Room
import com.assignment.shaadi.data.cache.LocalDatabase
import com.assignment.shaadi.other.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {
	@Singleton
	@Provides
	fun provideDatabase(
		@ApplicationContext app: Context
	) = Room.databaseBuilder(app, LocalDatabase::class.java, DB_NAME).build()

	@Singleton
	@Provides
	fun provideMatchInvitationDao(db: LocalDatabase) = db.matchInvitationDao()
}
