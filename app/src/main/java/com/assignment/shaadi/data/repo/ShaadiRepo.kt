package com.assignment.shaadi.data.repo

import androidx.lifecycle.LiveData
import com.assignment.shaadi.data.model.MatchInvitation
import com.assignment.shaadi.data.network.MatchNetworkResponse
import retrofit2.Response

interface ShaadiRepo {
	suspend fun getMatchNetworkResponse(): Response<MatchNetworkResponse>

	fun getMatchInvitations(): LiveData<List<MatchInvitation>>

	suspend fun insertMatchInvitations(matchInvitations: List<MatchInvitation>): Array<Long>

	suspend fun matchAttempted(matchInvitation: MatchInvitation): Long

	suspend fun getMatchInvitationsCount(): Int
}
