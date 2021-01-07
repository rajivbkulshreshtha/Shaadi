package com.assignment.shaadi.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Routes {
	//@GET("api/?results=10")
	@GET("api")
	suspend fun getNetworkMatches(@Query("results") results: Int = 10): Response<MatchNetworkResponse>
}
