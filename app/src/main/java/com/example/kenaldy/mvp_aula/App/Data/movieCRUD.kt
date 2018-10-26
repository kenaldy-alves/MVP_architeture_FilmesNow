package com.example.kenaldy.mvp_aula.App.Data

import com.example.kenaldy.mvp_aula.App.Data.Mapper.MovieDetailsMapper
import com.example.kenaldy.mvp_aula.App.Data.Mapper.MovieMapper
import com.example.kenaldy.mvp_aula.App.Data.Objects.Movies.MovieDB
import com.example.kenaldy.mvp_aula.App.Data.Objects.Movies.Movie
import io.realm.Realm
import io.realm.RealmConfiguration

class movieCRUD {
    //---------------------------------------------CREATE------------------------------------------------------------
    fun addFilmeDataBase(movie: Movie) {
        val config = RealmConfiguration.Builder()
                .name("Movie.realm")
                .build()
        val realm = Realm.getInstance(config)

        realm.beginTransaction()
            val movieDB = realm.createObject(MovieDB::class.java, movie.id)
            movieDB.title = movie.title
            movieDB.overview = movie.overview
            movieDB.poster_path = movie.poster_path
        realm.commitTransaction()
    }


    fun addFavoritosDataBase(movie: Movie): Boolean {
        val config = RealmConfiguration.Builder()
                .name("filmesFavoritos.realm")
                .build()
        val realm = Realm.getInstance(config)
        val filme = realm.where(MovieDB::class.java).equalTo("id", movie.id).findFirst()

        if (filme == null) {
            realm.beginTransaction()
                val movieDB = realm.createObject(MovieDB::class.java, movie.id)
                movieDB.title = movie.title
                movieDB.overview = movie.overview
                movieDB.poster_path = movie.poster_path
            realm.commitTransaction()
            return true
        }
        else{
            if(filme.id != movie.id){
                realm.beginTransaction()
                    val movieDB = realm.createObject(MovieDB::class.java, movie.id)
                    movieDB.title = movie.title
                    movieDB.overview = movie.overview
                    movieDB.poster_path = movie.poster_path
                realm.commitTransaction()
                return true
            }
        }
        return false
    }

    //-------------------------------------------DELETE--------------------------------------------------------------
    fun deleteFilmeDataBase(){
        val config = RealmConfiguration.Builder()
                .name("Movie.realm")
                .build()
        val realm = Realm.getInstance(config)

        realm.beginTransaction()
            val allMovies = realm.where(MovieDB::class.java).findAll()
            allMovies.deleteAllFromRealm()
        realm.commitTransaction()
    }

    //---------------------------------------------READ-----------------------------------------------------------
    fun mostraFilmesDB(): ArrayList<Movie> {
        val config = RealmConfiguration.Builder()
                .name("Movie.realm")
                .build()
        val realm = Realm.getInstance(config)
        val allMovies = realm.where(MovieDB::class.java).findAll()
        val list: ArrayList<Movie> = MovieMapper().mapperMovieDB(allMovies)

        return list
    }

    fun mostraFilmesDBFavoritos(): ArrayList<Movie> {
        val config = RealmConfiguration.Builder()
                .name("filmesFavoritos.realm")
                .build()
        val realm = Realm.getInstance(config)
        val allMovies = realm.where(MovieDB::class.java).findAll()
        val list: ArrayList<Movie> = MovieMapper().mapperMovieDB(allMovies)
        return list
    }


    fun mostraFilmeDetalhe(id_Movie : Int): Movie{
        val config = RealmConfiguration.Builder()
                .name("Movie.realm")
                .build()
        val realm = Realm.getInstance(config)
        val Movie = realm.where(MovieDB::class.java).equalTo("id",id_Movie).findFirst()
        val list = MovieDetailsMapper().mapperMovieDetailsDB(Movie)

        return list
    }

}