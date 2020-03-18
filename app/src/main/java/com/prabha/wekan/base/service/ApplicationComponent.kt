package com.prabha.wekan.base.service

import android.app.Application
import com.lynkdriver.lynk.factory.FactoryModule
import com.lynkdriver.lynk.network.di.NetworkModule
import com.prabha.wekan.base.WeKanApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, NetworkModule::class, ActivityBuilderModule::class, FactoryModule::class])
interface ApplicationComponent: AndroidInjector<WeKanApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}