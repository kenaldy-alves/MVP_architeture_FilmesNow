package com.example.kenaldy.mvp_aula.App.UI.Home

import com.example.kenaldy.mvp_aula.App.UI.Home.Adapter.HomeActivityFragment
import com.example.kenaldy.mvp_aula.App.UI.Home.Adapter.HomeAdapterToolbar

class HomeActivityPresenter(private var view: HomeContractActivity.HomeActivityView, private var pageAdapter: HomeAdapterToolbar): HomeContractActivity.HomeActivityPresenter {
    override fun setView(view: HomeContractActivity.HomeActivityView) {
        this.view = view
    }

    override fun configureHomeToolbar() {

        val nomesTab = ArrayList<String>()

        nomesTab.add("Filmes Populares")
        nomesTab.add("Series")

        for(i in 0 until nomesTab.size) {
            this.pageAdapter.addTab(HomeActivityFragment.newInstance(i), nomesTab[i])
        }

        view.configureAdapter()
    }
}