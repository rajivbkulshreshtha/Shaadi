package com.assignment.shaadi.data.cache.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assignment.shaadi.data.model.MatchInvitation

@Dao
interface MatchInvitationDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsertAll(matchInvitations: List<MatchInvitation>): Array<Long>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsert(matchInvitation: MatchInvitation): Long

	@Query("SELECT * FROM match_invitation ORDER BY uuid")
	fun getAll(): LiveData<List<MatchInvitation>>

	@Query("SELECT COUNT(*) from match_invitation")
	suspend fun count(): Int
}
