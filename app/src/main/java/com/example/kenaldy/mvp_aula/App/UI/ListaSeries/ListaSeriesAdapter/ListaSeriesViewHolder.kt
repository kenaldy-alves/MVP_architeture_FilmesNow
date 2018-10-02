package com.example.kenaldy.mvp_aula.App.UI.ListaSeries.ListaSeriesAdapter

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.content_series_recycler_view.view.*
import kotlinx.android.synthetic.main.lista_filmes_recycler_view.view.*

class ListaSeriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val imageSerie = itemView.imagemSeries
    val cardViewSeries = itemView.cardView_series
}