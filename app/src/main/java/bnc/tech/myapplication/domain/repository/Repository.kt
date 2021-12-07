package bnc.tech.myapplication.domain.repository

import bnc.tech.myapplication.domain.entity.MovieModel
import bnc.tech.myapplication.network.dto.BaseModel
import bnc.tech.myapplication.network.dto.BaseModelObj
import bnc.tech.myapplication.network.dto.Models
import io.reactivex.Single

interface Repository {

    fun getMovieUseCase(param:MovieModel): Single<BaseModel<Models.MovieResponse>>

    fun getMovieDetailUseCase(param:MovieModel): Single<BaseModelObj<Models.MovieDetailResponse>>
}