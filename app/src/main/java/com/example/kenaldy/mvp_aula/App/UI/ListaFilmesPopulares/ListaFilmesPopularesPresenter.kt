package com.example.kenaldy.mvp_aula.App.UI.ListaFilmesPopulares

import android.arch.lifecycle.MutableLiveData
import com.example.kenaldy.mvp_aula.App.Data.movieCRUD
import com.example.kenaldy.mvp_aula.App.Data.Mapper.MovieMapper
import com.example.kenaldy.mvp_aula.App.Data.Objects.Movies.Movie
import com.example.kenaldy.mvp_aula.App.Data.Response.Movie.JsonResponseMoviePopular
import com.example.kenaldy.mvp_aula.App.Data.Retrofit.RetrofitInializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaFilmesPopularesPresenter(private var view: MVP_Contract.ListaFilmesPopularesView?) : MVP_Contract.ListaFilmesPopularesPresenter {

    var showProgressBar: MutableLiveData<Boolean> = MutableLiveData()

    override fun setView(view: MVP_Contract.ListaFilmesPopularesView) {
        this.view = view
    }

    override fun getMoviePopularRequisition() {
        this.showProgressBar.value = true

        val call = RetrofitInializer().listMovieService().getLastedMovies()

        call.enqueue( object: Callback<JsonResponseMoviePopular> {
            override fun onFailure(call: Call<JsonResponseMoviePopular>?, t: Throwable?) {
                showProgressBar.value = false
                view?.mostraFilmes(movieCRUD().mostraFilmesDB())
                view?.mostraErroConexao()
            }

            override fun onResponse(call: Call<JsonResponseMoviePopular>?, response: Response<JsonResponseMoviePopular>?) {
                showProgressBar.value = false
                if (response != null) {
                    if (response.isSuccessful)
                        response.body()?.let {
                            val newMovieList: JsonResponseMoviePopular = it
                            val list: ArrayList<Movie>? = MovieMapper().mapperMovie(newMovieList.results)
                            view?.mostraFilmes(list!!)
                        }
                }
                else{
                    view?.mostraErro()
                }
            }
        })
    }

}