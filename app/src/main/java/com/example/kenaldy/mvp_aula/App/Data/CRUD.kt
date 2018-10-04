package com.example.kenaldy.mvp_aula.App.Data

import android.util.Log
import com.example.kenaldy.mvp_aula.App.Data.Mapper.MovieDetailsMapper
import com.example.kenaldy.mvp_aula.App.Data.Mapper.MovieMapper
import com.example.kenaldy.mvp_aula.App.Data.Objects.MovieDB
import com.example.kenaldy.mvp_aula.App.Data.Objects.Movies.Movie
import com.example.kenaldy.mvp_aula.App.Data.Objects.SerieDB
import com.example.kenaldy.mvp_aula.App.Data.Objects.Series.Serie
import io.realm.Realm
import io.realm.RealmConfiguration

class CRUD {

    //---------------------------------------------CREATE------------------------------------------------------------
    fun addFilmeDataBase(movie: Movie){
        val config = RealmConfiguration.Builder()
                .name("movie.realm")
                .build()
        val realm = Realm.getInstance(config)

        val movieRead = realm.where(MovieDB::class.java).findAll()

        if (movieRead.size == 0) {
            realm.beginTransaction()
            val movieDB = realm.createObject(MovieDB::class.java, movie.id)
                movieDB.title = movie.title
                movieDB.overview = movie.overview
            realm.commitTransaction()
        }

    }


    fun addSerieDataBase(serie: Serie){
        val config = RealmConfiguration.Builder()
                .name("serie.realm")
                .build()
        val realm = Realm.getInstance(config)
        val serieRead = realm.where(SerieDB::class.java).findAll()

        if (serieRead.size == 0) {
            realm.beginTransaction()
            val serieDB = realm.createObject(SerieDB::class.java, serie.id)
                serieDB.title = serie.title
                serieDB.overview = serie.overview
            realm.commitTransaction()
            Log.e("id", serieDB.id.toString())
        }
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

    fun deleteSerieDataBase(){
        val config = RealmConfiguration.Builder()
                .name("serie.realm")
                .build()
        val realm = Realm.getInstance(config)

        val allMovies = realm.where(MovieDB::class.java).findAll()

        realm.beginTransaction()
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