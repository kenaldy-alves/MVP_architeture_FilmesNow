package com.example.kenaldy.mvp_aula.App.UI.ListaSeries


import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.kenaldy.mvp_aula.App.Data.Objects.Serie
import com.example.kenaldy.mvp_aula.App.UI.ListaFilmesPopulares.ListaFilmesPopularesAdapter.MovieAdapter
import com.example.kenaldy.mvp_aula.App.UI.ListaSeries.ListaSeriesAdapter.ListaSeriesAdapter

import com.example.kenaldy.mvp_aula.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_series_main.*
import java.util.*

class ListaSeriesFragment : Fragment(), MVP_ListaSeriesContract.ListaSeriesView {

    private lateinit var contextFragment: Context
    private lateinit var serieAdapter: ListaSeriesAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.contextFragment = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_series_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureAdapter()

        val presenter = ListaSeriesPresenter(this)
        presenter.getSeriesRequisition()

        presenter.showProgressBar.observe(this, object : Observer<Boolean> {
            override fun onChanged(showprogressBar: Boolean?) {
                if(showprogressBar?:false){
                    progressBarSerie.visibility = View.VISIBLE
                }
                else{
                    progressBarSerie.visibility = View.GONE
                }
            }
        })
    }

    override fun mostraSeries(series: List<Serie>) {
        serieAdapter.setNewListMovies(series,contextFragment)
    }

    private fun configureAdapter(){// cria um adapter com uma lista vazia desacoplando a camada de rede com a camada de dominio
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val recyclerFilmes = minha_recyclerViewSeries

        serieAdapter = ListaSeriesAdapter()
        recyclerFilmes.adapter = serieAdapter
        recyclerFilmes.layoutManager = layoutManager
    }


    override fun mostraErro() {
        Toast.makeText(contextFragment,"Erro!!! Falha na obtenção da lista", Toast.LENGTH_SHORT).show()
    }

    override fun mostraErro3() {
        Toast.makeText(contextFragment,"Erro!!! Resposta Nula", Toast.LENGTH_SHORT).show()
    }

}
