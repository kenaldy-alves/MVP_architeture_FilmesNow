package com.example.kenaldy.mvp_aula.App.UI.ListaSeries

import com.example.kenaldy.mvp_aula.App.Data.Objects.Serie

interface MVP_ListaSeriesContract {
    interface ListaSeriesView{
        fun mostraErro()
        fun mostraSeries(series: List<Serie>)
    }

    interface ListaSeriesPresenter{
        fun view(view: ListaSeriesView)
        fun getSeriesRequisition()
    }
}