package com.example.kenaldy.mvp_aula.App.UI.SerieDetalhes

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.kenaldy.mvp_aula.App.Data.Objects.Movies.Movie
import com.example.kenaldy.mvp_aula.App.Data.Objects.Serie
import com.example.kenaldy.mvp_aula.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_filmes_detalhes.*
import kotlinx.android.synthetic.main.activity_filmes_home.*

import kotlinx.android.synthetic.main.activity_serie_detalhes.*

class SerieDetalhesActivity : AppCompatActivity(), SerieDetailsContract.SerieDetalhesView {
    private val KEY_SERIE : String = "serie"
    private lateinit var serie: Serie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_serie_detalhes)
        configureToolbar()

        configureToolbar()

        if (intent.hasExtra(KEY_SERIE)){
            serie = intent.getSerializableExtra(KEY_SERIE) as Serie
        }

        val presenter = SerieDetalhesPresenter(this)
        presenter.getSerieDetailRequisition(serie.id)

        presenter.showProgressBar.observe(this, object: Observer<Boolean> {
            override fun onChanged(showProgressbar: Boolean?) {
                if(showProgressbar?:false){
                    details_progressBarSerie.visibility = View.VISIBLE
                }
                else{
                    details_progressBarSerie.visibility = View.GONE
                }
            }

        })
    }


    fun configureToolbar(){
        val toolbar = minhs_toolbar
        setSupportActionBar(toolbar)
    }

    override fun mostraErro() {
        Toast.makeText(this,"Erro!!! Falha na obtenção da lista", Toast.LENGTH_SHORT).show()
    }

    override fun mostraSerie(serie: Serie) {

        titulo_serie_detalhes.text = serie.title
        descricao_serie.text = serie.overview
        Picasso.get().load("https://image.tmdb.org/t/p/w500/" + serie.poster_path).into(imageView_serie_detalhes)
    }
}
