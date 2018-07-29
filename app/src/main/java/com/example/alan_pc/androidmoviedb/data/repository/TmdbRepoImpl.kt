package com.example.alan_pc.androidmoviedb.data.repository

import com.example.alan_pc.androidmoviedb.BuildConfig
import com.example.alan_pc.androidmoviedb.data.api.TmdbApi
import com.example.alan_pc.androidmoviedb.data.entity.MovieResponseDto
import com.example.alan_pc.droidtv.BuildConfig
import com.example.alan_pc.droidtv.data.api.TmdbApi
import com.example.alan_pc.droidtv.data.entity.TvShowResponseDto
import com.example.alan_pc.droidtv.domain.business.tmdb.TmdbRepo
import io.reactivex.Observable
import javax.inject.Inject


class TmdbRepoImpl @Inject constructor(val tmdbApi: TmdbApi) : TmdbRepo {

    override fun searchMovies(page:String): Observable<MovieResponseDto> {
        return tmdbApi.getMoviesByName(BuildConfig.WB_BASIC,page)
    }
}