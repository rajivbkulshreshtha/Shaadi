package com.assignment.shaadi.ui.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.assignment.shaadi.R
import com.assignment.shaadi.di.MainFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

	@Inject
	lateinit var fragmentFactory: MainFragmentFactory

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		supportFragmentManager.fragmentFactory = fragmentFactory
		setContentView(R.layout.activity_main)
	}
}
// sample change 2