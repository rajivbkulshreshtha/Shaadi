package com.assignment.shaadi.data.network


import com.google.gson.annotations.SerializedName

data class MatchNetworkResponse(
	@SerializedName("info")
	var info: Info?,
	@SerializedName("results")
	var results: List<Result?>?
) {
	data class Info(
		@SerializedName("page")
		var page: Int?, // 1
		@SerializedName("results")
		var results: Int?, // 10
		@SerializedName("seed")
		var seed: String?, // 4e42394a556abd7c
		@SerializedName("version")
		var version: String? // 1.3
	)

	data class Result(
		@SerializedName("cell")
		var cell: String?, // 06-20-49-20-83
		@SerializedName("dob")
		var dob: Dob?,
		@SerializedName("email")
		var email: String?, // liam.roger@example.com
		@SerializedName("gender")
		var gender: String?, // male
		@SerializedName("id")
		var id: Id?,
		@SerializedName("location")
		var location: Location?,
		@SerializedName("login")
		var login: Login?,
		@SerializedName("name")
		var name: Name?,
		@SerializedName("nat")
		var nat: String?, // FR
		@SerializedName("phone")
		var phone: String?, // 05-29-77-76-95
		@SerializedName("picture")
		var picture: Picture?,
		@SerializedName("registered")
		var registered: Registered?
	) {
		data class Dob(
			@SerializedName("age")
			var age: Int?, // 54
			@SerializedName("date")
			var date: String? // 1967-01-13T13:49:24.427Z
		)

		data class Id(
			@SerializedName("name")
			var name: String?, // INSEE
			@SerializedName("value")
			var value: String? // 1NNaN92277584 42
		)

		data class Location(
			@SerializedName("city")
			var city: String?, // Pau
			@SerializedName("coordinates")
			var coordinates: Coordinates?,
			@SerializedName("country")
			var country: String?, // France
			@SerializedName("postcode")
			var postcode: Any?, // null
			@SerializedName("state")
			var state: String?, // Gironde
			@SerializedName("street")
			var street: Street?,
			@SerializedName("timezone")
			var timezone: Timezone?
		) {
			data class Coordinates(
				@SerializedName("latitude")
				var latitude: String?, // 0.5458
				@SerializedName("longitude")
				var longitude: String? // -179.0508
			)

			data class Street(
				@SerializedName("name")
				var name: String?, // Avenue Tony-Garnier
				@SerializedName("number")
				var number: Int? // 4256
			)

			data class Timezone(
				@SerializedName("description")
				var description: String?, // Kabul
				@SerializedName("offset")
				var offset: String? // +4:30
			)
		}

		data class Login(
			@SerializedName("md5")
			var md5: String?, // eb96cfb01d9eca936ce1a2bfc2f63764
			@SerializedName("password")
			var password: String?, // gator1
			@SerializedName("salt")
			var salt: String?, // 1j4gC8kp
			@SerializedName("sha1")
			var sha1: String?, // 3229c4c3573b8fea12e575b155897937b201b844
			@SerializedName("sha256")
			var sha256: String?, // 264702481857f01a25d4914a70062ac99983ea671524adcfb3c0bf6f8d9804c4
			@SerializedName("username")
			var username: String?, // whiterabbit799
			@SerializedName("uuid")
			var uuid: String? // 7ea61a5c-d2e1-47f2-86ec-c6dcf4fbccaf
		)

		data class Name(
			@SerializedName("first")
			var first: String?, // Liam
			@SerializedName("last")
			var last: String?, // Roger
			@SerializedName("title")
			var title: String? // Mr
		)

		data class Picture(
			@SerializedName("large")
			var large: String?, // https://randomuser.me/api/portraits/men/80.jpg
			@SerializedName("medium")
			var medium: String?, // https://randomuser.me/api/portraits/med/men/80.jpg
			@SerializedName("thumbnail")
			var thumbnail: String? // https://randomuser.me/api/portraits/thumb/men/80.jpg
		)

		data class Registered(
			@SerializedName("age")
			var age: Int?, // 5
			@SerializedName("date")
			var date: String? // 2016-08-25T00:49:58.061Z
		)
	}
}
