package com.assignment.shaadi.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.assignment.shaadi.ui.view.adapter.MatchInvitationAdapter
import com.assignment.shaadi.ui.view.fragment.MainFragment
import javax.inject.Inject

class MainFragmentFactory @Inject constructor(
	private val matchInvitationAdapter: MatchInvitationAdapter
) :
	FragmentFactory() {

	companion object {
		public const val TAG = "MainFragmentFactory"
	}

	override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

		return when (className) {
			MainFragment::class.java.name -> MainFragment(matchInvitationAdapter)
			else -> super.instantiate(classLoader, className)
		}
	}
}
