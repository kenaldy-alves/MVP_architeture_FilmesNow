package com.example.kenaldy.mvp_aula.App.UI.ListaFilmesPopulares.ListaFilmesPopularesAdapter

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.lista_filmes_recycler_view.view.*

class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val title = itemView.titulo
    val imageFilme = itemView.imagemFilme
    val cardView = itemView.movie_cardView
}