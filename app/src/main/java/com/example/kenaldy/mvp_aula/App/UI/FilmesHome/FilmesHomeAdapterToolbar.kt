package com.example.kenaldy.mvp_aula.App.UI.FilmesHome

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import com.example.kenaldy.mvp_aula.App.UI.ListaFilmesPopulares.ListaFilmesPopularesFragment
import com.example.kenaldy.mvp_aula.App.UI.ListaSeries.ListaSeriesFragment

class FilmesHomeAdapterToolbar(fragment: android.support.v4.app.FragmentManager): FragmentPagerAdapter(fragment) {
    private val tabNames : ArrayList<String> = ArrayList()
    private val fragment : ArrayList<Fragment> = ArrayList()

    fun addTab(fragment: Fragment, titulo: String){
        tabNames.add(titulo)
        this.fragment.add(fragment)
    }


    override fun getItem(position: Int): Fragment {
        if(position == 0) {
            var fragment = ListaFilmesPopularesFragment()
            return fragment
        }
           else{
            var fragment = ListaSeriesFragment()
            return fragment
        }

    }

    override fun getCount(): Int {
        return fragment.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabNames[position]
    }
}