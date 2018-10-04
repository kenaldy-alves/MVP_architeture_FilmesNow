package com.example.kenaldy.mvp_aula.App.UI.Home

import android.content.Context

interface HomeContractActivity {
    interface HomeActivityView{
        fun configureAdapter()
    }
    interface HomeActivityPresenter{
        fun setView(view: HomeActivityView)
        fun configureHomeToolbar()
        fun configureDataBase(context: Context)
    }
}