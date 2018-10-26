package com.example.kenaldy.mvp_aula.App.Data.Response.Movie

import com.example.kenaldy.mvp_aula.App.Data.Objects.Movies.Genres

class JsonResponseMovieDetails (
        val id: Int,
        val backdrop_path: String,
        val original_title: String,
        val genero: List<Genres>,
        val overview: String
)