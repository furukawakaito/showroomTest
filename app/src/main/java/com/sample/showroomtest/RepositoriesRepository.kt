package com.sample.showroomtest

import android.util.Log
import retrofit2.Response
import retrofit2.Retrofit

class RepositoriesRepository(
    private val retrofit: Retrofit
) {
    fun getRepositories(q: String?): Response<Repository> {
        val service = retrofit.create(GithubApi::class.java)
        return service.getRepository(q).execute()
    }
}