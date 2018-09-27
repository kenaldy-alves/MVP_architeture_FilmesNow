package com.example.kenaldy.mvp_aula.App.UI.FilmesDetalhes

import com.example.kenaldy.mvp_aula.App.Data.Objects.Movies.Movie

interface mvpContractDetailsMovie {

    interface MovieDetailView{
        fun mostraErro()
        fun mostraFilmes(filme: Movie)
    }
    interface MovieDetailsPresenter {
        fun setView(view:MovieDetailView)
        fun getMovieDetailsRequisition(id_movie: Int)
    }

}