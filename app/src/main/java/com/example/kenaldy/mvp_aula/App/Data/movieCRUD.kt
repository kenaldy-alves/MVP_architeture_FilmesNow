package com.example.kenaldy.mvp_aula.App.Data

import com.example.kenaldy.mvp_aula.App.Data.Mapper.MovieDetailsMapper
import com.example.kenaldy.mvp_aula.App.Data.Mapper.MovieMapper
import com.example.kenaldy.mvp_aula.App.Data.Objects.Movies.MovieDB
import com.example.kenaldy.mvp_aula.App.Data.Objects.Movies.Movie
import io.realm.Realm
import io.realm.RealmConfiguration

class movieCRUD {
    //---------------------------------------------CREATE------------------------------------------------------------
    fun addFilmeDataBase(movie: Movie){
        val config = RealmConfiguration.Builder()
                .name("movie.realm")
                .build()
        val realm = Realm.getInstance(config)

        realm.beginTransaction()
            val movieDB = realm.createObject(MovieDB::class.java, movie.id)
                movieDB.title = movie.title
                movieDB.overview = movie.overview
        realm.commitTransaction()
    }

    //-------------------------------------------DELETE--------------------------------------------------------------
    fun deleteFilmeDataBase(){
        val config = RealmConfiguration.Builder()
                .name("movie.realm")
                .build()
        val realm = Realm.getInstance(config)

        realm.beginTransaction()
        val allMovies = realm.where(MovieDB::class.java).findAll()
        allMovies.deleteAllFromRealm()
        realm.commitTransaction()
    }

    fun mostraFilmesDB(): ArrayList<Movie> {
        val config = RealmConfiguration.Builder()
                .name("movie.realm")
                .build()
        val realm = Realm.getInstance(config)
        val allMovies = realm.where(MovieDB::class.java).findAll()
        val list: ArrayList<Movie> = MovieMapper().mapperMovieDB(allMovies)

        return list
    }

    fun mostraFilmeDetalhe(id_Movie : Int): Movie{
        val config = RealmConfiguration.Builder()
                .name("movie.realm")
                .build()
        val realm = Realm.getInstance(config)
        val Movie = realm.where(MovieDB::class.java).equalTo("id",id_Movie).findAll()
        val list = MovieDetailsMapper().mapperMovieDetailsDB(Movie)

        return list
    }

}