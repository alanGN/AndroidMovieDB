package com.example.alan_pc.androidmoviedb.data.repository

import com.example.alan_pc.androidmoviedb.BuildConfig
import com.example.alan_pc.androidmoviedb.data.api.TmdbApi
import com.example.alan_pc.androidmoviedb.data.entity.MovieResponseDto
import com.example.alan_pc.androidmoviedb.domain.business.tmdb.TmdbRepo
import io.reactivex.Observable
import javax.inject.Inject


class TmdbRepoImpl @Inject constructor(val tmdbApi: TmdbApi) : TmdbRepo {

    override fun getMoviesByName(page: String, textToSearch: String): Observable<MovieResponseDto> {
        return tmdbApi.getMoviesByName(BuildConfig.WB_BASIC, textToSearch, page, false) //Is an under 18 app so we can't search adult content in this app ;) :P
    }
}