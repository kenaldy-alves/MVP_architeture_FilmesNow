package com.example.kenaldy.mvp_aula.App.UI.FilmesDetalhes

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.kenaldy.mvp_aula.App.Data.Objects.Movies.Movie
import com.example.kenaldy.mvp_aula.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_filmes_detalhes.*
import kotlinx.android.synthetic.main.activity_filmes_home.*

@Suppress("DEPRECATION")
class FilmesDetalhesActivity : AppCompatActivity(), mvpContractDetailsMovie.MovieDetailView{

    private val KEY_FILME : String = "movie"
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filmes_detalhes)
        configureToolbar()

        if (intent.hasExtra(KEY_FILME)){
            movie = intent.getSerializableExtra(KEY_FILME) as Movie
        }

        val presenter = FilmesDetalhesPresenter(this)
        presenter.getMovieDetailsRequisition(movie.id)

        presenter.showProgressBar.observe(this, object: Observer<Boolean> {
            override fun onChanged(showProgressbar: Boolean?) {
                if(showProgressbar?:false){
                    details_progressBar.visibility = View.VISIBLE
                }
                else{
                    details_progressBar.visibility = View.GONE
                }
            }

        })
    }

    fun configureToolbar(){
        val toolbar = minhs_toolbar
        setSupportActionBar(toolbar)
    }

    override fun mostraFilmes(filme: Movie) {

        Log.e("filme:", filme.title)
        titulo_filmes_detalhes.text = filme.title
        descricao.text = filme.overview
        Picasso.get().load("https://image.tmdb.org/t/p/w500/" + filme.poster_path).into(imageView_filme_detalhes)
    }

    override fun mostraErro() {
        Toast.makeText(this,"Erro!!! Falha na obtenção da lista 2222", Toast.LENGTH_SHORT).show()
    }

}
