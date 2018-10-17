package com.example.kenaldy.mvp_aula.App.UI.Favoritos.FavoritosAdapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.kenaldy.mvp_aula.App.Data.Objects.Movies.Movie
import com.example.kenaldy.mvp_aula.App.UI.Home.HomeActivity
import com.example.kenaldy.mvp_aula.App.UI.ListaFilmesPopulares.ListaFilmesPopularesAdapter.MovieViewHolder
import com.example.kenaldy.mvp_aula.R
import com.squareup.picasso.Picasso

class FilmesFavoritosAdapter: RecyclerView.Adapter<FilmesFavoritosViewHolder>() {
    var newListMovie : List<Movie>? = null
    lateinit var context: Context

    fun setNewListMovies(movies: List<Movie>, context: Context){
        this.newListMovie = movies
        this.context = context
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): FilmesFavoritosViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.favoritos_recycler_view, parent, false)
        return FilmesFavoritosViewHolder(view)
    }

    override fun getItemCount(): Int {
        if(newListMovie?.size  == null)
            return 0
        else
            return newListMovie?.size!!
    }

    override fun onBindViewHolder(holder: FilmesFavoritosViewHolder, position: Int) {
        val lista = newListMovie

        holder?.let {
            it.textFilmes.text = lista!![position].title
            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + lista[position].poster_path).into(it.imageFilme)
            it.cardView.setOnClickListener {
                if (context is HomeActivity) {
                    (context as HomeActivity).navigationMovieDetails(lista[position])
                }
            }
        }
    }

}