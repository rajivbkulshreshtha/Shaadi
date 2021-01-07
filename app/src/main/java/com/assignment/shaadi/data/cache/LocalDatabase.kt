package com.assignment.shaadi.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.assignment.shaadi.data.cache.dao.MatchInvitationDao
import com.assignment.shaadi.data.model.MatchInvitation

@Database(entities = [MatchInvitation::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
	abstract fun matchInvitationDao(): MatchInvitationDao
}
