package com.example.kenaldy.mvp_aula.App.UI.Favoritos

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.kenaldy.mvp_aula.App.Data.movieCRUD

class FilmesFavoritosPresenter(private var view: MvpContract_FilmesFavoritos.filmesFavoritosView): MvpContract_FilmesFavoritos.filmesFavoritosPresenter {
    var showProgressBar: MutableLiveData<Boolean> = MutableLiveData()

    override fun view(view: MvpContract_FilmesFavoritos.filmesFavoritosView) {
        this.view = view
    }

    override fun filmesFavoritosrequisition() {
        this.showProgressBar.value = true
        view.mostraFilme(movieCRUD().mostraFilmesDBFavoritos())
        showProgressBar.value = false
    }
}