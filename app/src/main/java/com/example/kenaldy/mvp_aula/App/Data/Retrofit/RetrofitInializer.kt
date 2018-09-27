package com.example.kenaldy.mvp_aula.App.Data.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitInializer {

    val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    fun listMovieService() = retrofit.create(WebServices::class.java)
}