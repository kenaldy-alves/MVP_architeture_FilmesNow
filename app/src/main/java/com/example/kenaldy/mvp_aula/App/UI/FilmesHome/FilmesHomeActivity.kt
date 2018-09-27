package com.example.kenaldy.mvp_aula.App.UI.FilmesHome

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.example.kenaldy.mvp_aula.App.Data.Objects.Movies.Movie
import com.example.kenaldy.mvp_aula.App.UI.FilmesDetalhes.FilmesDetalhesActivity
import com.example.kenaldy.mvp_aula.R
import kotlinx.android.synthetic.main.activity_filmes_home.*

class FilmesHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filmes_home)
        configureToolbar()

        val pageAdapter = FilmesHomeAdapterToolbar(supportFragmentManager)
        val nomesTab = ArrayList<String>()

        nomesTab.add("Filmes Populares")
        nomesTab.add("Series")

        for(i in 0 until nomesTab.size) {
            pageAdapter.addTab(FilmesHomeActivityFragment.newInstance(i), nomesTab[i])
        }

        view_pager.adapter = pageAdapter
        tabs.setupWithViewPager(view_pager)


    }

    fun configureToolbar(){
        val toolbar = minhs_toolbar
        setSupportActionBar(toolbar)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun navigation(movie: Movie) {
            val intent = Intent(this, FilmesDetalhesActivity::class.java)
            intent.putExtra("movie", movie)
            intent.flags = (Intent.FLAG_ACTIVITY_NEW_TASK)
            this.startActivity(intent)
    }

}
