package bnc.tech.myapplication.domain.dataSource

import androidx.paging.DataSource
import bnc.tech.myapplication.network.dto.Models
import javax.inject.Inject

class MovieDetailDataSourceFactory @Inject constructor(
    private val dataSource: MovieDetailPositionalDataSource
) : DataSource.Factory<Int, Models.MovieDetailResponse>() {

    //val moviesDataSourceLiveData = MutableLiveData<moviePositionalDataSource>()
    fun setFilter(filter: String) {
        dataSource.setFilter(filter)
    }

    override fun create(): DataSource<Int, Models.MovieDetailResponse> {
       // photosDataSourceLiveData.postValue(dataSource)
            return dataSource
    }

}