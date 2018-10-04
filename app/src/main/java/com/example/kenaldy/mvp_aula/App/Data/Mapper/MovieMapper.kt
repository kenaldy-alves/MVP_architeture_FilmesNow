package com.example.kenaldy.mvp_aula.App.Data.Mapper

import android.util.Log
import com.example.kenaldy.mvp_aula.App.Data.CRUD
import com.example.kenaldy.mvp_aula.App.Data.Objects.MovieDB
import com.example.kenaldy.mvp_aula.App.Data.Objects.Movies.Movie
import com.example.kenaldy.mvp_aula.App.Data.Response.Movie.MovieResult
import io.realm.RealmResults

class MovieMapper {
    fun mapperMovie(listMovie: List<MovieResult>): ArrayList<Movie>? {
        CRUD().deleteFilmeDataBase()
        var movieList: ArrayList<Movie> = ArrayList()

        for (lista in listMovie){
            val filme = Movie(lista.id, lista.original_title, lista.overview, lista.poster_path)
            CRUD().addFilmeDataBase(filme)
            movieList?.add(filme)
        }
        return movieList
    }

    fun mapperMovieDB(listMovie: RealmResults<MovieDB>): ArrayList<Movie> {
        var movieList: ArrayList<Movie> = ArrayList()

        for (lista in listMovie){
            Log.e(lista.title, lista.id.toString())
            val filme = Movie(lista.id, lista.title, lista.overview, lista.poster_path)
            movieList?.add(filme)
        }
        return movieList
    }

}