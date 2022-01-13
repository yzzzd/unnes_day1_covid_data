package com.friend.datacovid

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.friend.datacovid.api.ApiService
import com.friend.datacovid.data.DataCovid
import com.google.gson.Gson
import com.nuryazid.core.api.ApiObserver
import com.nuryazid.core.extension.toObject

class MainViewModel: ViewModel() {

    private val apiService = ApiService.getInstance()

    val covidDataPositif = MutableLiveData<DataCovid>()

    fun getDataPositive() {
        ApiObserver({ apiService.positif() }) {
            val data = it.toObject<DataCovid>(Gson())
            covidDataPositif.postValue(data)
        }
    }
}