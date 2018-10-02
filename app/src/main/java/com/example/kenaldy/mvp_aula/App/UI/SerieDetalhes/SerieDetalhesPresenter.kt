package com.example.kenaldy.mvp_aula.App.UI.SerieDetalhes

import android.arch.lifecycle.MutableLiveData
import com.example.kenaldy.mvp_aula.App.Data.Mapper.MovieDetailsMapper
import com.example.kenaldy.mvp_aula.App.Data.Mapper.SerieDetailsMapper
import com.example.kenaldy.mvp_aula.App.Data.Objects.Movies.Movie
import com.example.kenaldy.mvp_aula.App.Data.Objects.Serie
import com.example.kenaldy.mvp_aula.App.Data.Response.Movie.JsonResponseMovieDetails
import com.example.kenaldy.mvp_aula.App.Data.Response.Series.JsonResponseSerieDetails
import com.example.kenaldy.mvp_aula.App.Data.Retrofit.RetrofitInializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SerieDetalhesPresenter(private var view: SerieDetailsContract.SerieDetalhesView): SerieDetailsContract.SerieDetalhesPresenter {
    var showProgressBar: MutableLiveData<Boolean> = MutableLiveData()

    override fun setView(view: SerieDetailsContract.SerieDetalhesView) {
        this.view = view
    }

    override fun getSerieDetailRequisition(id: Int) {

        this.showProgressBar.value = true

        val call = RetrofitInializer().listMovieService().getSerieDetails(id,"ad756a6a2bf1e24028a941c80255bff5")

        call.enqueue(object: Callback<JsonResponseSerieDetails> {
            override fun onFailure(call: Call<JsonResponseSerieDetails>?, t: Throwable?) {
                showProgressBar.value = false
                view?.mostraErro()
            }

            override fun onResponse(call: Call<JsonResponseSerieDetails>?, response: Response<JsonResponseSerieDetails>?) {
                showProgressBar.value = false
                if (response != null) {
                    if (response.isSuccessful)
                        response?.body()?.let {
                            val movieDetails: JsonResponseSerieDetails = it
                            val serie : Serie = SerieDetailsMapper().MapperSerieDetails(movieDetails)
                            view.mostraSerie(serie)
                        }
                }
                else
                    view.mostraErro()
            }

        })
    }

}