package com.example.kenaldy.mvp_aula.App.Data.Mapper

import com.example.kenaldy.mvp_aula.App.Data.Objects.Serie
import com.example.kenaldy.mvp_aula.App.Data.Response.Series.SeriesResult


class SerieMapper {
    fun mapperSerie(listSeries: List<SeriesResult>): ArrayList<Serie>{
        var seriesList: ArrayList<Serie> = ArrayList()

        for(lista in listSeries){
            val serie = Serie(lista.id, lista.original_name, lista.overview, lista.poster_path)
            seriesList.add(serie)
        }
        return seriesList
    }
}