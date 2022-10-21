package com.rappi.moviedetail_domain.di

import com.rappi.moviedetail_domain.repository.MovieDetailRepository
import com.rappi.moviedetail_domain.use_case.GetDetailRemote
import com.rappi.moviedetail_domain.use_case.GetVideoRemote
import com.rappi.moviedetail_domain.use_case.MovieDetailsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object MovieDetailDomainModule {

    @ViewModelScoped
    @Provides
    fun provideMovieDetailUseCases(
        repository: MovieDetailRepository,
    ): MovieDetailsUseCases {
        return MovieDetailsUseCases(
            getVideo = GetVideoRemote(repository),
            getDetail = GetDetailRemote(repository),
        )
    }
}