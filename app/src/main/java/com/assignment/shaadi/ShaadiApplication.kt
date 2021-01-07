package com.assignment.shaadi

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShaadiApplication : Application() {

	companion object {
		public const val TAG = "ShaadiApplication"
	}

	override fun onCreate() {
		super.onCreate()
	}

}
