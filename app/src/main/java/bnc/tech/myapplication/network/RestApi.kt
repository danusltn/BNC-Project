package bnc.tech.myapplication.network

import bnc.tech.myapplication.network.dto.BaseModel
import bnc.tech.myapplication.network.dto.BaseModelObj
import bnc.tech.myapplication.network.dto.Models
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RestApi {

    companion object {
        const val BASE_URL = "https://private-anon-0f2273d58f-bncfetest.apiary-mock.com/"
    }


    @GET("movies")
    fun getMovies(): Single<BaseModel<Models.MovieResponse>>

    @GET("movies/{id}")
    fun getMoviesDetail(@Path("id") query:String? = "1"): Single<BaseModelObj<Models.MovieDetailResponse>>

}