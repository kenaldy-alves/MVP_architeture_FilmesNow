package com.example.kenaldy.mvp_aula.App.UI.ListaSeries

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.kenaldy.mvp_aula.App.Data.Mapper.SerieMapper
import com.example.kenaldy.mvp_aula.App.Data.Objects.Serie
import com.example.kenaldy.mvp_aula.App.Data.Response.Series.JsonResponseSeries
import com.example.kenaldy.mvp_aula.App.Data.Retrofit.RetrofitInializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaSeriesPresenter(private var view: MVP_ListaSeriesContract.ListaSeriesView?): MVP_ListaSeriesContract.ListaSeriesPresenter {

    var showProgressBar: MutableLiveData<Boolean> = MutableLiveData()

    override fun view(view: MVP_ListaSeriesContract.ListaSeriesView) {
       this.view = view
    }

    override fun getSeriesRequisition() {
        this.showProgressBar.value = true

        val call  = RetrofitInializer().listMovieService().getSeries()

        call.enqueue(object: Callback<JsonResponseSeries>{
            override fun onFailure(call: Call<JsonResponseSeries>?, t: Throwable?) {
                showProgressBar.value = false
                view?.mostraErro()
            }

            override fun onResponse(call: Call<JsonResponseSeries>?, response: Response<JsonResponseSeries>?) {
                showProgressBar.value = false
                if (response != null) {
                    if (response.isSuccessful)
                        response.body()?.let {
                            val newMovieList: JsonResponseSeries = it
                            Log.e("teste", newMovieList.results[0].original_name)
                            val list: ArrayList<Serie>? = SerieMapper().mapperSerie(newMovieList.results)
                            view?.mostraSeries(list!!)
                        }
                    else
                        view?.mostraErro3()

                }
                else{
                    showProgressBar.value = false
                    view?.mostraErro3()
                }
            }
        })
    }

}
