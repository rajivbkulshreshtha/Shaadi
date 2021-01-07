package com.assignment.shaadi.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.assignment.shaadi.other.INVITATION_STATUS

@Entity(tableName = "match_invitation")
data class MatchInvitation(
	@PrimaryKey
	@ColumnInfo(name = "uuid")
	var uuid: String, // 529bb092-c983-45c6-8eb8-7f2c7154770e
	@ColumnInfo(name = "firstName")
	var firstName: String?, // John
	@ColumnInfo(name = "lastName")
	var lastName: String?, // Doe
	@ColumnInfo(name = "imageUrl")
	var imageUrl: String?, // https://randomuser.me/api/portraits/women/8.jpg
	@ColumnInfo(name = "age")
	var age: Int?, // 41
	@ColumnInfo(name = "email")
	var email: String?, // brooke.harvey@example.com
	@ColumnInfo(name = "city")
	var city: String?, // City of London
	@ColumnInfo(name = "state")
	var state: String?, // Strathclyde
	@ColumnInfo(name = "country")
	var country: String?, // United Kingdom
	@ColumnInfo(name = "invitation_status")
	var invitation_status: String = INVITATION_STATUS.PENDING, // pending
)
