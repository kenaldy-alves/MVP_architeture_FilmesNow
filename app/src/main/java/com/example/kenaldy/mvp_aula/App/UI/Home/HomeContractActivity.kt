package com.example.kenaldy.mvp_aula.App.UI.Home

interface HomeContractActivity {
    interface HomeActivityView{
        fun configureAdapter()
    }
    interface HomeActivityPresenter{
        fun setView(view: HomeActivityView)
        fun configureHomeToolbar()
    }
}