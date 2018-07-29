package com.example.alan_pc.androidmoviedb.data.api

import com.example.alan_pc.androidmoviedb.BuildConfig
import com.example.alan_pc.androidmoviedb.data.entity.MovieResponseDto
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Created by ALAN-PC on 29/07/2018
 */

interface TmdbApi {

    @Headers(BuildConfig.WB_CONTENT_TYPE_KEY + BuildConfig.WB_CONTENT_TYPE_VALUE, "Accept: application/json")
    @GET("search/movie")
    fun getMoviesByName(@Query("api_key") api_key: String, @Query("query") query: String, @Query("page") page: String, @Query("include_adult") include_adult: Boolean): Observable<MovieResponseDto>

}