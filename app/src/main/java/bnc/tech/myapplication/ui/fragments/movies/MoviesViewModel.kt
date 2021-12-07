package bnc.tech.myapplication.ui.fragments.movies

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import bnc.tech.myapplication.domain.dataSource.MovieDataSourceFactory
import bnc.tech.myapplication.network.dto.Models
import bnc.tech.myapplication.ui.base.BaseViewModel
import javax.inject.Inject

private const val PAGE_SIZE = 20
private const val INITIAL_LOAD_SIZE_HINT = 40

class MoviesViewModel @Inject constructor(
    private val dataSourceFactory: MovieDataSourceFactory
) : BaseViewModel<PagedList<Models.MovieResponse>>() {

    var cachedFilter: String = ""

    fun setFilter(filter: String) {
        dataSourceFactory.setFilter(if (cachedFilter.isEmpty()) filter else cachedFilter)
    }

    init {
        createLiveData()
    }

    fun createLiveData() {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
            .setPageSize(PAGE_SIZE)
            .build()
        this.stateLiveData = LivePagedListBuilder(dataSourceFactory, pagedListConfig).build()
    }
}