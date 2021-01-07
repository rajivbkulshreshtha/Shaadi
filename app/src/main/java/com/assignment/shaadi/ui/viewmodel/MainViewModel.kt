package com.assignment.shaadi.ui.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.assignment.shaadi.R
import com.assignment.shaadi.data.model.MatchInvitation
import com.assignment.shaadi.data.network.MatchNetworkResponse
import com.assignment.shaadi.data.repo.ShaadiRepo
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


class MainViewModel @ViewModelInject constructor(
	@ApplicationContext val context: Context,
	private val repo: ShaadiRepo,
	@Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel(), LifecycleObserver {

	companion object {
		const val TAG = "MainViewModel"
	}

	val matches = repo.getMatchInvitations()
	val error = MutableLiveData<String>()
	val loading = MutableLiveData<Boolean>()


	fun getMatches() = viewModelScope.launch(Dispatchers.IO) {
		if (checkInternet(context)) {
			loading.postValue(true)
			try {
				val response = repo.getMatchNetworkResponse()
				if (response.isSuccessful) {
					val matchInvitations = matchNetworkResponseConverter(response.body())
					repo.insertMatchInvitations(matchInvitations)
					loading.postValue(false)
				} else {
					loading.postValue(false)
					error.postValue(context.getString(R.string.msg_something_wrong))
				}
			} catch (e: Exception) {
				loading.postValue(false)
				error.postValue(context.getString(R.string.msg_something_wrong))
			}
		} else {
			loading.postValue(false)
			error.postValue(context.getString(R.string.msg_no_internet))
		}
	}

	fun onMatchAttempted(matchInvitation: MatchInvitation, invitation_status: String) =
		viewModelScope.launch(Dispatchers.IO) {
			matchInvitation.invitation_status = invitation_status
			repo.matchAttempted(matchInvitation)
		}

	private fun matchNetworkResponseConverter(matchNetworkResponse: MatchNetworkResponse?): List<MatchInvitation> {

		var matchInvitations: List<MatchInvitation>? = null

		matchNetworkResponse?.let { response ->
			matchInvitations = response.results?.map {
				MatchInvitation(
					uuid = it?.login?.uuid ?: UUID.randomUUID().toString(),
					firstName = it?.name?.first,
					lastName = it?.name?.last,
					imageUrl = it?.picture?.large,
					age = it?.dob?.age,
					email = it?.email,
					city = it?.location?.city,
					state = it?.location?.state,
					country = it?.location?.country
				)
			}
		}

		return matchInvitations ?: mutableListOf()
	}

	private fun checkInternet(context: Context): Boolean {
		val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
		val networks = cm.allNetworks
		var hasInternet = false
		if (networks.isNotEmpty()) {
			for (network in networks) {
				val nc = cm.getNetworkCapabilities(network)
				if (nc!!.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) hasInternet =
					true
			}
		}
		return hasInternet
	}
}
