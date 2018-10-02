package com.example.kenaldy.mvp_aula.App.Data.Retrofit

import com.example.kenaldy.mvp_aula.App.Data.Response.Movie.JsonResponseMovieDetails
import com.example.kenaldy.mvp_aula.App.Data.Response.Movie.JsonResponseMoviePopular
import com.example.kenaldy.mvp_aula.App.Data.Response.Series.JsonResponseSerieDetails
import com.example.kenaldy.mvp_aula.App.Data.Response.Series.JsonResponseSeries
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebServices {
    @GET("movie/popular?")
    fun getLastedMovies(@Query("api_key") api_key: String = "ad756a6a2bf1e24028a941c80255bff5",
                        @Query("language") language: String = "pt-BR") : Call<JsonResponseMoviePopular>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id_movie: Int,
                        @Query("api_key") api_key: String,
                        @Query("language") language: String = "pt-BR"): Call<JsonResponseMovieDetails>

    @GET("tv/popular?")
    fun getSeries(@Query("api_key") api_key: String = "ad756a6a2bf1e24028a941c80255bff5"): Call<JsonResponseSeries>

    @GET("tv/{tv_id}")
    fun getSerieDetails(@Path("tv_id") id_tv: Int,
                        @Query("api_key") api_key: String,
                        @Query("language") language: String = "pt-BR"): Call<JsonResponseSerieDetails>
}