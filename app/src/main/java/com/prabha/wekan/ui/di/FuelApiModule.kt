package com.prabha.wekan.ui.di
import com.prabha.wekan.ui.repository.FuelStationApiService
import com.prabha.wekan.ui.repository.FuelStationRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class FuelApiModule {
    @FuelStationScope
    @Provides
    fun provideAbortApiService(retrofit: Retrofit): FuelStationApiService {
        return retrofit.create(FuelStationApiService::class.java)
    }

    @FuelStationScope
    @Provides
    fun provideDashboardRepository(apiService: FuelStationApiService): FuelStationRepository {
        return FuelStationRepository(apiService)
    }


}