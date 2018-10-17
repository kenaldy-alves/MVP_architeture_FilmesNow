package com.example.kenaldy.mvp_aula.App.UI.ListaSeries


import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.kenaldy.mvp_aula.App.Data.Objects.Series.Serie
import com.example.kenaldy.mvp_aula.App.UI.ListaSeries.ListaSeriesAdapter.ListaSeriesAdapter

import com.example.kenaldy.mvp_aula.R
import com.example.kenaldy.mvp_aula.R.layout.fragment_series_main
import kotlinx.android.synthetic.main.fragment_series_main.*

class ListaSeriesFragment : Fragment(), MVP_ListaSeriesContract.ListaSeriesView {

    private lateinit var contextFragment: Context
    private lateinit var serieAdapter: ListaSeriesAdapter
    private val presenter = ListaSeriesPresenter(this)

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
    }

    override fun onResume() {
        super.onResume()
        presenter.getSeriesRequisition()
        configureProgressBar()

        swipe_refresh_series.setOnRefreshListener (object  : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                presenter.getSeriesRequisition()
            }
        })
    }

    override fun mostraSeries(series: List<Serie>) {
        serieAdapter.setNewListMovies(series,contextFragment)
        swipe_refresh_series.isRefreshing = false
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

    private fun configureProgressBar(){
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
}
