package bnc.tech.myapplication.domain.interactor.base

import bnc.tech.myapplication.domain.executer.PostExecutionThread
import bnc.tech.myapplication.domain.executer.UseCaseExecutor
import bnc.tech.myapplication.domain.repository.Repository
import bnc.tech.myapplication.network.dto.BaseModel
import bnc.tech.myapplication.network.dto.Models
import io.reactivex.Single

/**
 * @param Responses The response value emitted by the Observable.
 * @param Params The request value.
 */
abstract class SingleUseCase<Responses, Params>(useCaseExecutor: UseCaseExecutor,
                                                postExecutionThread: PostExecutionThread,
                                                protected var repository: Repository) :
    UseCase<Single<Responses>, Params>(useCaseExecutor, postExecutionThread) {

    open fun execute(params: Params): Single<Responses> {
        return interact(params).applySchedulers()
    }

    protected abstract fun interact(params: Params): Single<Responses>

}