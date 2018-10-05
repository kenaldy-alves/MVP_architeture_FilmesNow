package com.example.kenaldy.mvp_aula.App.UI.ListaFilmesPopulares

import com.example.kenaldy.mvp_aula.App.Data.Objects.Movies.Movie

interface MVP_Contract {
    interface ListaFilmesPopularesView{
        fun mostraErro()
        fun mostraErroConexao()
        fun mostraFilmes(filmes: List<Movie>)
    }

    interface ListaFilmesPopularesPresenter{
        fun setView(view: ListaFilmesPopularesView)         //interface para receber a view da camada de visão
        fun getMoviePopularRequisition()                    //interface para requisição de lista
    }

}