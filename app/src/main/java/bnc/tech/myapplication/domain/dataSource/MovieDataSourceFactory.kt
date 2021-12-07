package bnc.tech.myapplication.domain.dataSource

import androidx.paging.DataSource
import bnc.tech.myapplication.network.dto.Models
import javax.inject.Inject

class MovieDataSourceFactory @Inject constructor(
    private val dataSource : MoviePositionalDataSource
) : DataSource.Factory<Int, Models.MovieResponse>() {

    //val moviesDataSourceLiveData = MutableLiveData<moviePositionalDataSource>()
    fun setFilter(filter: String) {
        dataSource.setFilter(filter)
    }

    override fun create(): DataSource<Int, Models.MovieResponse> {
       // photosDataSourceLiveData.postValue(dataSource)
            return dataSource
    }

}