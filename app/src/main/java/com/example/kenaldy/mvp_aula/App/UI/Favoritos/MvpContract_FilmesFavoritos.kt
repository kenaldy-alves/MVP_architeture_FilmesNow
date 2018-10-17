package com.example.kenaldy.mvp_aula.App.UI.Favoritos

import com.example.kenaldy.mvp_aula.App.Data.Objects.Movies.Movie

interface MvpContract_FilmesFavoritos {
    interface filmesFavoritosView{
        fun mostraErro()
        fun mostraFilme(filmes: ArrayList<Movie>)
    }
    interface filmesFavoritosPresenter{
        fun view(view: filmesFavoritosView)
        fun filmesFavoritosrequisition()
    }
}