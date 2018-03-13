package com.example.ivantelisman.kotlinpracticeapp.retrofit

import com.example.ivantelisman.kotlinpracticeapp.Example
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

import java.util.*


/**
 * Created by ivantelisman on 2/8/18.
 */

interface  NetworkApi {
    @GET("posts/")
    fun getApiCallContents(): Observable<Response<List<Example>>>

    @GET("photos/1")
    fun getApiCallPhoto(): Observable<Response<Example>>
}
