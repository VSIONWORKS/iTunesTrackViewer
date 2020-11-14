package com.example.itunestrackviewer.data.remote

import com.example.itunestrackviewer.data.model.ApiData
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

/**
 * Service Class that will provide the fetch data.
 * With retry capability for auto reconnecting to the internet
 */
class ApiService: ApiServiceInterface {

    override fun getItems(): Single<ApiData> {
        val url = "https://itunes.apple.com/search?term=star&amp;country=au&amp;media=movie&amp;all"
        return Rx2AndroidNetworking.get(url)
            .build()
            .getObjectSingle(ApiData::class.java).retry()
    }
}