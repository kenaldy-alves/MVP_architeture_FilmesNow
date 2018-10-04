package com.example.kenaldy.mvp_aula.App.UI.ListaSeries.ListaSeriesAdapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.kenaldy.mvp_aula.App.Data.Objects.Series.Serie
import com.example.kenaldy.mvp_aula.App.UI.Home.HomeActivity
import com.example.kenaldy.mvp_aula.R
import com.squareup.picasso.Picasso

class ListaSeriesAdapter(): RecyclerView.Adapter<ListaSeriesViewHolder>() {
    var newListSerie : List<Serie>? = null
    lateinit var context: Context

    fun setNewListMovies(series: List<Serie>, context: Context){
        this.newListSerie = series
        this.context = context
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ListaSeriesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.content_series_recycler_view, parent, false)
        return ListaSeriesViewHolder(view)
    }

    override fun getItemCount(): Int {
        if(newListSerie?.size  == null)
            return 0
        else
            return newListSerie?.size!!
    }

    override fun onBindViewHolder(holder: ListaSeriesViewHolder, position: Int) {
        val list = newListSerie

        holder.let {
            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + list!![position].poster_path).into(it.imageSerie)
            it.cardViewSeries.setOnClickListener{
                if(context is HomeActivity){
                    (context as HomeActivity).navigationSerieDetails(list[position])
                }
            }
        }

    }
}