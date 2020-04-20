package com.lynkdriver.lynk.factory

import androidx.lifecycle.ViewModelProvider
import com.lynkdriver.lynk.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class FactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}