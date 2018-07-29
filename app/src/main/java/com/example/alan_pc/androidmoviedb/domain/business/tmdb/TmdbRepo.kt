package com.example.alan_pc.androidmoviedb.domain.business.tmdb

import com.example.alan_pc.androidmoviedb.data.entity.MovieResponseDto
import io.reactivex.Observable

/**
 * Created by ALAN-PC on 29/07/2018
 */
interface TmdbRepo {
    fun getMoviesByName(page: String, textToSearch: String): Observable<MovieResponseDto>
}