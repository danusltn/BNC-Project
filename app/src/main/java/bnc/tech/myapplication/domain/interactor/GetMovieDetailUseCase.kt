package bnc.tech.myapplication.domain.interactor

import bnc.tech.myapplication.domain.entity.MovieModel
import bnc.tech.myapplication.domain.executer.PostExecutionThread
import bnc.tech.myapplication.domain.executer.UseCaseExecutor
import bnc.tech.myapplication.domain.interactor.base.SingleUseCase
import bnc.tech.myapplication.domain.repository.Repository
import bnc.tech.myapplication.network.dto.BaseModel
import bnc.tech.myapplication.network.dto.BaseModelObj
import bnc.tech.myapplication.network.dto.Models
import io.reactivex.Single
import javax.inject.Inject

class GetMovieDetailUseCase @Inject
constructor(
    useCaseExecutor: UseCaseExecutor,
    postExecutionThread: PostExecutionThread,
    repository: Repository
) : SingleUseCase<BaseModelObj<Models.MovieDetailResponse>, MovieModel>(useCaseExecutor, postExecutionThread, repository) {
    override fun interact(params: MovieModel): Single<BaseModelObj<Models.MovieDetailResponse>> {
        return repository.getMovieDetailUseCase(params)
    }
}