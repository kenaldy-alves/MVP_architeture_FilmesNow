package com.example.kenaldy.mvp_aula.App.UI.Favoritos.FavoritosAdapter

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.favoritos_recycler_view.view.*
import kotlinx.android.synthetic.main.lista_filmes_recycler_view.view.*

class FilmesFavoritosViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val imageFilme = itemView.imagemFilmeFavoritos
    val textFilmes = itemView.textFavoritos
    val cardView = itemView.movie_cardViewFavoritos
}