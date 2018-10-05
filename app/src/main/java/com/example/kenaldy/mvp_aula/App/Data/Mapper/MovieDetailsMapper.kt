package com.example.kenaldy.mvp_aula.App.Data.Mapper

import com.example.kenaldy.mvp_aula.App.Data.Objects.Movies.MovieDB
import com.example.kenaldy.mvp_aula.App.Data.Objects.Movies.Movie
import com.example.kenaldy.mvp_aula.App.Data.Response.Movie.JsonResponseMovieDetails
import io.realm.RealmResults

class MovieDetailsMapper {

    fun MapperMovieDetails(movieDetailsJson: JsonResponseMovieDetails): Movie {
       val movie = Movie(movieDetailsJson.id, movieDetailsJson.original_title, movieDetailsJson.overview, movieDetailsJson.backdrop_path)
        return movie
    }

    fun mapperMovieDetailsDB(movieDetailsJson: RealmResults<MovieDB>): Movie {
        val movie = Movie(movieDetailsJson[0]?.id, movieDetailsJson[0]?.title, movieDetailsJson[0]?.overview, movieDetailsJson[0]?.poster_path)
        return movie
    }
}