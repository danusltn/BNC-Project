package bnc.tech.myapplication.domain.dataSource

import androidx.paging.PositionalDataSource
import bnc.tech.myapplication.network.dto.Models
import javax.inject.Inject
import bnc.tech.myapplication.domain.entity.MovieModel
import bnc.tech.myapplication.domain.interactor.GetMovieDetailUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber


class MovieDetailPositionalDataSource @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : PositionalDataSource<Models.MovieDetailResponse>(), Disposable {

    private var disposing = false
    override fun isDisposed(): Boolean {
        return disposing
    }

    override fun dispose() {
        disposing = true
        compositeDisposable.clear()
    }

    private var filter: String = ""
    fun setFilter(filter: String) {
        this.filter = filter
    }

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

//    private fun computeCount(): Int {
//        // actual count code here
//        return getMovieUseCase.execute(MovieModel()).blockingGet()
//    }


    private fun loadRangeInternal(startPosition: Int, loadCount: Int) {
        // actual load code here

        val pageNum = startPosition / loadCount + 1
        val disposable = getMovieDetailUseCase.execute(MovieModel(filter)).subscribe({ response ->
            // Timber.i("emitter size is"+response.size)
        }, { t: Throwable? ->
            Timber.e(t)
        })
        compositeDisposable.add(disposable)

    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Models.MovieDetailResponse>) {

        val pageNum = params.startPosition / params.loadSize + 1
//        val disposable = getMovieDetailUseCase.execute(MovieModel(filter)).subscribe({ response ->
//            // Timber.i("emitter size is"+response.size)
//            callback.onResult(response.response);
//        }, { t: Throwable? ->
//            Timber.e(t)
//        })
//        compositeDisposable.add(disposable)

    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Models.MovieDetailResponse>) {
        val totalCount = 2000
        val position = computeInitialLoadPosition(params, totalCount)
        val loadSize = computeInitialLoadSize(params, position, totalCount)

        val pageNum = position / loadSize + 1
//        val disposable = getMovieDetailUseCase.execute(MovieModel(filter)).subscribe({ response ->
//            callback.onResult(response.response,0,34)
//        }, { t: Throwable? ->
//            Timber.e(t)
//        })
//        compositeDisposable.add(disposable)

    }
}