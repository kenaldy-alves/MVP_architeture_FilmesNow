package com.example.kenaldy.mvp_aula.App.UI.Home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.example.kenaldy.mvp_aula.App.Data.Objects.Movies.Movie
import com.example.kenaldy.mvp_aula.App.Data.Objects.Series.Serie
import com.example.kenaldy.mvp_aula.App.UI.FilmesDetalhes.FilmesDetalhesActivity
import com.example.kenaldy.mvp_aula.App.UI.Home.Adapter.HomeAdapterToolbar
import com.example.kenaldy.mvp_aula.App.UI.SerieDetalhes.SerieDetalhesActivity
import com.example.kenaldy.mvp_aula.R
import kotlinx.android.synthetic.main.activity_filmes_home.*

class HomeActivity : AppCompatActivity(), HomeContractActivity.HomeActivityView{

    private val pageAdapter = HomeAdapterToolbar(supportFragmentManager)
    val presenter = HomeActivityPresenter(this, pageAdapter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filmes_home)
        presenter.configureHomeToolbar()
    }

    override fun onResume() {
        super.onResume()
        configureToolbar()
        presenter.configureDataBase(this)
    }

    fun configureToolbar(){
        val toolbar = minhs_toolbar
        setSupportActionBar(toolbar)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun configureAdapter() {
        view_pager.adapter = pageAdapter
        tabs.setupWithViewPager(view_pager)
    }



    fun navigationMovieDetails(movie: Movie) {
        val intent = Intent(this, FilmesDetalhesActivity::class.java)
        intent.putExtra("movie", movie)
        intent.flags = (Intent.FLAG_ACTIVITY_NEW_TASK)
        this.startActivity(intent)
    }

    fun navigationSerieDetails(serie: Serie) {
        val intent = Intent(this, SerieDetalhesActivity::class.java)
        intent.putExtra("serie", serie)
        intent.flags = (Intent.FLAG_ACTIVITY_NEW_TASK)
        this.startActivity(intent)
    }

}
