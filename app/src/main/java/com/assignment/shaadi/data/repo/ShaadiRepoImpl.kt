package com.assignment.shaadi.data.repo

import com.assignment.shaadi.data.cache.dao.MatchInvitationDao
import com.assignment.shaadi.data.model.MatchInvitation
import com.assignment.shaadi.data.network.Routes
import javax.inject.Inject

class ShaadiRepoImpl @Inject constructor(
	var routes: Routes,
	var matchInvitationDao: MatchInvitationDao
) : ShaadiRepo {
	companion object {
		public const val TAG = "ShaadiRepoImpl"
	}

	override suspend fun getMatchNetworkResponse() = routes.getNetworkMatches()

	override fun getMatchInvitations() = matchInvitationDao.getAll()

	override suspend fun insertMatchInvitations(matchInvitations: List<MatchInvitation>) =
		matchInvitationDao.upsertAll(matchInvitations)

	override suspend fun matchAttempted(matchInvitation: MatchInvitation) =
		matchInvitationDao.upsert(matchInvitation)

	override suspend fun getMatchInvitationsCount() = matchInvitationDao.count()
}
