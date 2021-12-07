package bnc.tech.myapplication.data.repository

import bnc.tech.myapplication.domain.entity.MovieModel
import bnc.tech.myapplication.domain.repository.Repository
import bnc.tech.myapplication.network.RestApi
import bnc.tech.myapplication.network.dto.BaseModel
import bnc.tech.myapplication.network.dto.BaseModelObj
import bnc.tech.myapplication.network.dto.Models
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val restApi: RestApi) : Repository {
    override fun getMovieUseCase(param: MovieModel): Single<BaseModel<Models.MovieResponse>> {
        return Single.create { emitter ->
            restApi.getMovies().subscribe({ response ->
                Timber.d("response : %s", response)
                emitter.onSuccess(response)
            }, {
                emitter.onError(it)
            })
        }
    }

    override fun getMovieDetailUseCase(param: MovieModel): Single<BaseModelObj<Models.MovieDetailResponse>> {
        return Single.create { emitter ->
            restApi.getMoviesDetail().subscribe({ response ->
                Timber.d("response : %s", response)
                emitter.onSuccess(response)
            }, {
                emitter.onError(it)
            })
        }
    }
}