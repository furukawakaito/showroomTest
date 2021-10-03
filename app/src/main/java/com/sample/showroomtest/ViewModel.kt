package com.sample.showroomtest


import androidx.lifecycle.*
import androidx.lifecycle.ViewModel


class ViewModel : ViewModel() {

    private var result: MutableLiveData<Result<Repository>> = MutableLiveData()
    val resultList: LiveData<Result<Repository>> get() = result

    private val provider: Provider = Provider()
    private val repository: RepositoriesRepository = RepositoriesRepository(provider.retrofit)

    fun searchRepository(q: String) {
        repository.getRepositories(q).also { response ->
            if (response.isSuccessful) {
                this.result.postValue(Result.success(response.body()!!))
            } else {
                this.result.postValue(Result.failure(Throwable(response.errorBody()!!.toString())))
            }

        }
    }
}