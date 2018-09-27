package com.example.kenaldy.mvp_aula.App.Data.Mapper

import com.example.kenaldy.mvp_aula.App.Data.Objects.Movies.Movie
import com.example.kenaldy.mvp_aula.App.Data.Response.Movie.MovieResult

class MovieMapper {
    fun mapperMovie(listMovie: List<MovieResult>): ArrayList<Movie>? {
        var movieList: ArrayList<Movie> = ArrayList()

        for (lista in listMovie){
            val filme = Movie(lista.id, lista.original_title, lista.overview, lista.poster_path)
            movieList?.add(filme)
        }
        return movieList
    }
}