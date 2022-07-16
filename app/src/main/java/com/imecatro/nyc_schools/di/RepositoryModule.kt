package com.imecatro.nyc_schools.di

import com.imecatro.nyc_schools.network.SchoolRepository
import com.imecatro.nyc_schools.network.SchoolRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesJokesRepository(
        schoolsRepositoryImpl: SchoolRepositoryImp
    ): SchoolRepository

}