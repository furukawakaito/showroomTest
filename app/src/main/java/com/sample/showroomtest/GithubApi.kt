package com.sample.showroomtest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    @GET("/search/repositories")
    fun getRepository(@Query("q") q: String?): Call<Repository>
}