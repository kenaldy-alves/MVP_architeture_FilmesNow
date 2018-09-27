package com.example.kenaldy.mvp_aula.App.Data.Mapper

import com.example.kenaldy.mvp_aula.App.Data.Objects.Movies.Movie
import com.example.kenaldy.mvp_aula.App.Data.Response.Movie.JsonResponseMovieDetails

class MovieDetailsMapper {

    fun MapperMovieDetails(movieDetailsJson: JsonResponseMovieDetails): Movie {
       val movie = Movie(movieDetailsJson.id, movieDetailsJson.original_title, movieDetailsJson.overview, movieDetailsJson.backdrop_path)
        return movie
    }
}