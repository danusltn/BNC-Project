package bnc.tech.myapplication.di.app

import dagger.Module
import dagger.android.ContributesAndroidInjector
import bnc.tech.myapplication.ui.fragments.movieDetail.MovieDetailFragment
import bnc.tech.myapplication.ui.fragments.movies.MoviesFragment

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMoviesFragment(): MoviesFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieDetailFragment(): MovieDetailFragment

}