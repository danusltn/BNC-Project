package bnc.tech.myapplication.di.app

import dagger.Module
import dagger.android.ContributesAndroidInjector
import bnc.tech.myapplication.ui.MainActivity


@Module()
abstract class BuilderModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

}