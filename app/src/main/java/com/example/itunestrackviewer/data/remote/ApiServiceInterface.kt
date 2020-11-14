package com.example.itunestrackviewer.data.remote

import com.example.itunestrackviewer.data.model.ApiData
import io.reactivex.Single

/**
 * Main Remote Interface
 */
interface ApiServiceInterface {
    fun getItems(): Single<ApiData>
}