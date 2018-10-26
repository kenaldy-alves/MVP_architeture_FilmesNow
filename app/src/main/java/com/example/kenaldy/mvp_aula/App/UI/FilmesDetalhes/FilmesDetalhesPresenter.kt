package com.example.kenaldy.mvp_aula.App.UI.FilmesDetalhes

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.kenaldy.mvp_aula.App.Data.movieCRUD
import com.example.kenaldy.mvp_aula.App.Data.Mapper.MovieDetailsMapper
import com.example.kenaldy.mvp_aula.App.Data.Objects.Movies.Movie
import com.example.kenaldy.mvp_aula.App.Data.Response.Movie.JsonResponseMovieDetails
import com.example.kenaldy.mvp_aula.App.Data.Retrofit.RetrofitInializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmesDetalhesPresenter(private var view: mvpContractDetailsMovie.MovieDetailView): mvpContractDetailsMovie.MovieDetailsPresenter {
     var showProgressBar: MutableLiveData<Boolean> = MutableLiveData()


    override fun setView(view: mvpContractDetailsMovie.MovieDetailView) {
        this.view = view
    }

    override fun getMovieDetailsRequisition(id_movie: Int) {

        this.showProgressBar.value = true

        val call = RetrofitInializer().listMovieService().getMovieDetails(id_movie)

        call.enqueue(object: Callback<JsonResponseMovieDetails>{
            override fun onFailure(call: Call<JsonResponseMovieDetails>?, t: Throwable?) {
                showProgressBar.value = false
                view.mostraFilmes(movieCRUD().mostraFilmeDetalhe(id_movie))
            }

            override fun onResponse(call: Call<JsonResponseMovieDetails>?, response: Response<JsonResponseMovieDetails>?) {
                showProgressBar.value = false
                if (response != null) {
                    if (response.isSuccessful)
                        response.body()?.let {
                            val movieDetails: JsonResponseMovieDetails = it
                            val movie : Movie = MovieDetailsMapper().MapperMovieDetails(movieDetails)
                            view.mostraFilmes(movie)
                        }
                }
                else
                    view.mostraFilmes(movieCRUD().mostraFilmeDetalhe(id_movie))
            }

        })
    }
}