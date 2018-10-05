package com.example.kenaldy.mvp_aula.App.Data.Mapper

import com.example.kenaldy.mvp_aula.App.Data.Objects.Series.Serie
import com.example.kenaldy.mvp_aula.App.Data.Objects.Series.SerieDB
import com.example.kenaldy.mvp_aula.App.Data.Response.Series.JsonResponseSerieDetails
import io.realm.RealmResults

class SerieDetailsMapper {
    fun MapperSerieDetails(serieDetailsJson: JsonResponseSerieDetails): Serie {
        val serie = Serie(serieDetailsJson.id, serieDetailsJson.name, serieDetailsJson.overview, serieDetailsJson.backdrop_path)
        return serie
    }

    fun mapperSerieDetailsDB(serieDetailsJson: RealmResults<SerieDB>): Serie {
        val serie = Serie(serieDetailsJson[0]?.id, serieDetailsJson[0]?.title, serieDetailsJson[0]?.overview, serieDetailsJson[0]?.poster_path)
        return serie
    }
}