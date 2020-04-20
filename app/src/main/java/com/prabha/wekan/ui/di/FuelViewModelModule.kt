package com.prabha.wekan.ui.di

import androidx.lifecycle.ViewModel
import com.prabha.wekan.base.ViewModelKey
import com.prabha.wekan.ui.viewmodel.FuelStationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FuelViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(FuelStationViewModel::class)
    protected abstract fun abortViewModel(abortViewModelModule: FuelStationViewModel): ViewModel
}