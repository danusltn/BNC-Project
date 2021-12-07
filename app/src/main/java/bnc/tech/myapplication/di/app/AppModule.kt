package bnc.tech.myapplication.di.app

import android.content.Context
import dagger.Module
import dagger.Provides
import bnc.tech.myapplication.App
import bnc.tech.myapplication.data.extractor.NetworkJobExecutor
import bnc.tech.myapplication.di.scope.ForApplication
import bnc.tech.myapplication.domain.executer.PostExecutionThread
import bnc.tech.myapplication.domain.executer.UseCaseExecutor
import bnc.tech.myapplication.domain.repository.ConnectivityManager
import bnc.tech.myapplication.ui.executer.UiThreadExecutor
import bnc.tech.myapplication.util.ConnectivityManagerImp
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideUseCaseExecutor(): UseCaseExecutor {
        return NetworkJobExecutor()
    }

    @Provides
    @JvmStatic
    @Singleton
    fun postExecutionThread(): PostExecutionThread = UiThreadExecutor()


    @Provides
    @JvmStatic
    @ForApplication
    fun provideContext(app: App): Context = app.applicationContext

    @Provides
    @JvmStatic
    @Singleton
    fun provideConnectivityManager(connectivityManagerImp: ConnectivityManagerImp)
            : ConnectivityManager = connectivityManagerImp
}
