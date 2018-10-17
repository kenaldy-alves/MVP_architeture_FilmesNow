package com.example.kenaldy.mvp_aula.App.UI.Favoritos

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.kenaldy.mvp_aula.App.Data.Objects.Movies.Movie
import com.example.kenaldy.mvp_aula.App.UI.Favoritos.FavoritosAdapter.FilmesFavoritosAdapter

import com.example.kenaldy.mvp_aula.R
import kotlinx.android.synthetic.main.fragment_filmes_favoritos.*

class FilmesFavoritosFragment : Fragment(), MvpContract_FilmesFavoritos.filmesFavoritosView {

    private val presenter = FilmesFavoritosPresenter(this)
    private lateinit var contextFragment: Context
    private lateinit var filmesAdapter: FilmesFavoritosAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.contextFragment = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_filmes_favoritos, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureAdapter()
    }

    override fun onResume() {
        super.onResume()
        presenter.filmesFavoritosrequisition()
        configureProgressBar()
    }

    private fun configureProgressBar() {
        presenter.showProgressBar.observe(this, object: Observer<Boolean> {
            override fun onChanged(showProgressbar: Boolean?) {
                if(showProgressbar?:false){
                    progressBarFavoritos.visibility = View.VISIBLE
                }
                else{
                    progressBarFavoritos.visibility = View.GONE
                }
            }
        })
    }

    override fun mostraFilme(filmes: ArrayList<Movie>) {
        if(filmes == null)
            mostraErro()
        else
            filmesAdapter.setNewListMovies(filmes,contextFragment)
    }

    override fun mostraErro() {
        Toast.makeText(contextFragment,"Lista vazia!!!!", Toast.LENGTH_SHORT).show()
    }

    private fun configureAdapter(){// cria um adapter com uma lista vazia desacoplando a camada de rede com a camada de dominio
        val layoutManager = LinearLayoutManager(contextFragment, LinearLayoutManager.VERTICAL, true)
        val recyclerFilmes = minha_recyclerView_favoritos

        filmesAdapter = FilmesFavoritosAdapter()
        recyclerFilmes.adapter = filmesAdapter
        recyclerFilmes.layoutManager = layoutManager
    }

}
