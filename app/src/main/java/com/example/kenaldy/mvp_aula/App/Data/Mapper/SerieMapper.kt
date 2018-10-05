package com.example.kenaldy.mvp_aula.App.Data.Mapper

import android.util.Log
import com.example.kenaldy.mvp_aula.App.Data.Objects.Series.SerieDB
import com.example.kenaldy.mvp_aula.App.Data.Objects.Series.Serie
import com.example.kenaldy.mvp_aula.App.Data.Response.Series.SeriesResult
import com.example.kenaldy.mvp_aula.App.Data.serieCRUD
import io.realm.RealmResults


class SerieMapper {
    fun mapperSerie(listSeries: List<SeriesResult>): ArrayList<Serie>{
        var seriesList: ArrayList<Serie> = ArrayList()
        serieCRUD().deleteSerieDataBase()

        for(lista in listSeries){
            val serie = Serie(lista.id, lista.original_name, lista.overview, lista.poster_path)
            serieCRUD().addSerieDataBase(serie)
            seriesList.add(serie)
        }
        return seriesList
    }


    fun mapperSerieDB(listSerie: RealmResults<SerieDB>): ArrayList<Serie> {
        var serieList: ArrayList<Serie> = ArrayList()

        for (lista in listSerie){
            Log.e(lista.title, lista.id.toString())
            val serie = Serie(lista.id, lista.title, lista.overview, lista.poster_path)
            serieList?.add(serie)
        }
        return serieList
    }

}