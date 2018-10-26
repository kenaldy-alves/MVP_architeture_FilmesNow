package com.example.kenaldy.mvp_aula.App.UI.FilmesDetalhes

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.kenaldy.mvp_aula.App.Data.Objects.Movies.Movie
import com.example.kenaldy.mvp_aula.App.Data.movieCRUD
import com.example.kenaldy.mvp_aula.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_filmes_detalhes.*
import kotlinx.android.synthetic.main.activity_filmes_home.*
import kotlinx.android.synthetic.main.fragment_series_main.*

@Suppress("DEPRECATION")
class FilmesDetalhesActivity : AppCompatActivity(), mvpContractDetailsMovie.MovieDetailView{

    private val KEY_FILME : String = "movie"
    private lateinit var movie: Movie
    val presenter = FilmesDetalhesPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filmes_detalhes)
        configureToolbar()

        if (intent.hasExtra(KEY_FILME)){
            movie = intent.getSerializableExtra(KEY_FILME) as Movie
        }

        presenter.getMovieDetailsRequisition(movie.id!!)
        configureProgressBar()
    }

    fun configureToolbar(){
        val toolbar = minhs_toolbar
        setSupportActionBar(toolbar)
    }

    override fun mostraFilmes(filme: Movie) {
        titulo_filmes_detalhes.text = filme.title
        descricao.text = filme.overview

        Picasso.get().load("https://image.tmdb.org/t/p/w500/" + filme.poster_path).into(imageView_filme_detalhes)


        fab.setOnClickListener{
            if(movieCRUD().addFavoritosDataBase(filme))
                Snackbar.make(container, "Filme adicionado aos favoritos!!", Snackbar.LENGTH_SHORT).show()
            else
                Snackbar.make(container, "Este filme já foi adicionado aos favoritos!!!", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun mostraErro() {
        Toast.makeText(this,"Erro!!! Falha na obtenção da lista ", Toast.LENGTH_SHORT).show()
    }


    private fun configureProgressBar() {
        presenter.showProgressBar.observe(this, object : Observer<Boolean> {
            override fun onChanged(showprogressBar: Boolean?) {
                if(showprogressBar?:false){
                    fab.visibility = View.GONE
                    details_progressBar.visibility = View.VISIBLE
                }
                else{
                    details_progressBar.visibility = View.GONE
                    fab.visibility = View.VISIBLE
                }
            }
        })
    }
}
