package com.assignment.shaadi.di

import com.assignment.shaadi.data.network.Routes
import com.assignment.shaadi.other.Constants.BASE_URL
import com.assignment.shaadi.other.Constants.OKHTTP_TIMEOUT_SECONDS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

	@Singleton
	@Provides
	fun provideOkHttp(): OkHttpClient {
		val builder = OkHttpClient.Builder()
			.readTimeout(OKHTTP_TIMEOUT_SECONDS, TimeUnit.SECONDS)
			.connectTimeout(OKHTTP_TIMEOUT_SECONDS, TimeUnit.SECONDS)
			.retryOnConnectionFailure(true)
			.addInterceptor(HttpLoggingInterceptor().apply {
				this.level = HttpLoggingInterceptor.Level.BODY
			})
		return builder.build()
	}


	@Singleton
	@Provides
	fun provideRoutes(
		okHttpClient: OkHttpClient
	) = Retrofit.Builder()
		.client(okHttpClient)
		.baseUrl(BASE_URL)
		.addConverterFactory(GsonConverterFactory.create())
		.build()
		.create(Routes::class.java)

}
