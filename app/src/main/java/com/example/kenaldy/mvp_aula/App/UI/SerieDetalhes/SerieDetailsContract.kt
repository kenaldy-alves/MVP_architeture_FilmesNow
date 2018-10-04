package com.example.kenaldy.mvp_aula.App.UI.SerieDetalhes

import com.example.kenaldy.mvp_aula.App.Data.Objects.Series.Serie


interface SerieDetailsContract {
    interface SerieDetalhesView {
       fun mostraErro()
       fun mostraSerie(serie: Serie)
    }

    interface SerieDetalhesPresenter {
        fun setView(view: SerieDetalhesView)
        fun getSerieDetailRequisition(id: Int)
    }
}