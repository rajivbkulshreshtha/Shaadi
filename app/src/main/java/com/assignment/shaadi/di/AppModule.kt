package com.assignment.shaadi.di

import android.content.Context
import com.assignment.shaadi.R
import com.assignment.shaadi.data.cache.dao.MatchInvitationDao
import com.assignment.shaadi.data.network.Routes
import com.assignment.shaadi.data.repo.ShaadiRepo
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.assignment.shaadi.data.repo.ShaadiRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {


	@Singleton
	@Provides
	fun provideRepo(
		routes: Routes,
		matchInvitationDao: MatchInvitationDao
	): ShaadiRepo = ShaadiRepoImpl(routes, matchInvitationDao)

	@Singleton
	@Provides
	fun provideGlideInstance(
		@ApplicationContext context: Context
	) = Glide.with(context).setDefaultRequestOptions(
		RequestOptions()
			.placeholder(R.drawable.ic_placeholder)
			.error(R.drawable.ic_placeholder)
	)

}
