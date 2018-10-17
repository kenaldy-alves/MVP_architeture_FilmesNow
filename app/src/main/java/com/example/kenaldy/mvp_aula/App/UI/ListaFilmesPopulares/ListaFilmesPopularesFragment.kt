package com.example.kenaldy.mvp_aula.App.UI.ListaFilmesPopulares


import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.kenaldy.mvp_aula.App.Data.Objects.Movies.Movie
import com.example.kenaldy.mvp_aula.App.UI.FilmesDetalhes.FilmesDetalhesActivity
import com.example.kenaldy.mvp_aula.App.UI.ListaFilmesPopulares.ListaFilmesPopularesAdapter.MovieAdapter
import com.example.kenaldy.mvp_aula.R
import kotlinx.android.synthetic.main.activity_main.*


class ListaFilmesPopularesFragment : Fragment(), MVP_Contract.ListaFilmesPopularesView  {

    private lateinit var filmesAdapter: MovieAdapter
    private lateinit var context2 : Context
    private val presenter = ListaFilmesPopularesPresenter(this)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context2 = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureAdapter()
    }

    override fun onResume() {
        super.onResume()
        presenter.getMoviePopularRequisition()
        configureProgressBar()

        swipe_refresh.setOnRefreshListener (object : SwipeRefreshLayout.OnRefreshListener{
            override fun onRefresh() {
                presenter.getMoviePopularRequisition()
            }
        })
    }

    private fun configureProgressBar() {
        presenter.showProgressBar.observe(this, object: Observer<Boolean> {
            override fun onChanged(showProgressbar: Boolean?) {
                if(showProgressbar?:false){
                    progressBar.visibility = View.VISIBLE
                }
                else{
                    progressBar.visibility = View.GONE
                }
            }
        })
    }

    override fun mostraFilmes(filmes: List<Movie>) {
        filmesAdapter.setNewListMovies(filmes,context2)
        swipe_refresh.isRefreshing = false
    }

    override fun mostraErro() {
        Toast.makeText(context2,"Erro!!! Falha na obtenção da lista", Toast.LENGTH_SHORT).show()
    }

    private fun configureAdapter(){// cria um adapter com uma lista vazia desacoplando a camada de rede com a camada de dominio
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val recyclerFilmes = minha_recyclerView

        filmesAdapter = MovieAdapter()
        recyclerFilmes.adapter = filmesAdapter
        recyclerFilmes.layoutManager = layoutManager
    }

    override fun mostraErroConexao() {
        Snackbar.make(minha_recyclerView, "Sem conexão",Snackbar.LENGTH_SHORT).show()
    }

}
