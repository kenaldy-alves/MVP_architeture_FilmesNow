package com.example.kenaldy.mvp_aula.App.Data

import com.example.kenaldy.mvp_aula.App.Data.Mapper.MovieDetailsMapper
import com.example.kenaldy.mvp_aula.App.Data.Mapper.SerieDetailsMapper
import com.example.kenaldy.mvp_aula.App.Data.Mapper.SerieMapper
import com.example.kenaldy.mvp_aula.App.Data.Objects.Movies.MovieDB
import com.example.kenaldy.mvp_aula.App.Data.Objects.Movies.Movie
import com.example.kenaldy.mvp_aula.App.Data.Objects.Series.SerieDB
import com.example.kenaldy.mvp_aula.App.Data.Objects.Series.Serie
import com.squareup.picasso.Picasso
import io.realm.Realm
import io.realm.RealmConfiguration

class serieCRUD {

    fun addSerieDataBase(serie: Serie){
        val config = RealmConfiguration.Builder()
                .name("serie.realm")
                .build()
        val realm = Realm.getInstance(config)

        realm.beginTransaction()
            val serieDB = realm.createObject(SerieDB::class.java, serie.id)
            serieDB.title = serie.title
            serieDB.overview = serie.overview
            serieDB.poster_path = serie.poster_path
        realm.commitTransaction()

    }

    fun deleteSerieDataBase(){
        val config = RealmConfiguration.Builder()
                .name("serie.realm")
                .build()
        val realm = Realm.getInstance(config)

        val allSeries = realm.where(SerieDB::class.java).findAll()

        realm.beginTransaction()
        allSeries.deleteAllFromRealm()
        realm.commitTransaction()
    }

    fun mostraSerieDB(): ArrayList<Serie> {
        val config = RealmConfiguration.Builder()
                .name("serie.realm")
                .build()
        val realm = Realm.getInstance(config)
        val allSeries = realm.where(SerieDB::class.java).findAll()
        val list: ArrayList<Serie> = SerieMapper().mapperSerieDB(allSeries)

        return list
    }

    fun mostraSerieDetalhe(id_Serie : Int): Serie {
        val config = RealmConfiguration.Builder()
                .name("serie.realm")
                .build()
        val realm = Realm.getInstance(config)
        val Serie = realm.where(SerieDB::class.java).equalTo("id",id_Serie).findAll()
        val list = SerieDetailsMapper().mapperSerieDetailsDB(Serie)

        return list
    }


}