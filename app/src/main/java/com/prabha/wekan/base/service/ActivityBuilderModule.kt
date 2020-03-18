package com.prabha.wekan.base.service

import com.prabha.wekan.ui.di.FuelApiModule
import com.prabha.wekan.ui.di.FuelStationScope
import com.prabha.wekan.ui.di.FuelViewModelModule
import com.prabha.wekan.ui.view.FuelStationsListActivty
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilderModule {

    @FuelStationScope
    @ContributesAndroidInjector(modules = [FuelViewModelModule::class, FuelApiModule::class])
    abstract fun contributeFuelStationsListActivty(): FuelStationsListActivty



}