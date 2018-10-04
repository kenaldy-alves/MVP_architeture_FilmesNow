package com.example.kenaldy.mvp_aula.App.Data.Mapper

import com.example.kenaldy.mvp_aula.App.Data.Objects.Series.Serie
import com.example.kenaldy.mvp_aula.App.Data.Response.Series.JsonResponseSerieDetails

class SerieDetailsMapper {
    fun MapperSerieDetails(serieDetailsJson: JsonResponseSerieDetails): Serie {
        val serie = Serie(serieDetailsJson.id, serieDetailsJson.name, serieDetailsJson.overview, serieDetailsJson.backdrop_path)
        return serie
    }
}