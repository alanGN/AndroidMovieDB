package com.example.alan_pc.androidmoviedb.domain.business.tmdb

import com.example.alan_pc.androidmoviedb.data.entity.MovieResponseDto
import com.example.alan_pc.androidmoviedb.domain.business.tmdb.interfaces.MovieInterface
import com.example.alan_pc.androidmoviedb.domain.business.tmdb.TmdbRepo
import com.example.alan_pc.androidmoviedb.domain.mapper.MovieMapper
import com.example.alan_pc.androidmoviedb.presentation.general.GeneralView
import com.example.alan_pc.androidmoviedb.presentation.general.reactive.GeneralBoImpl
import com.example.alan_pc.androidmoviedb.presentation.general.reactive.GeneralSubscriber
import javax.inject.Inject

/**
 * Created by ALAN-PC on 29/07/2018
 */
class TmdbBo @Inject constructor(var tmdbRepo: TmdbRepo, var movieMapper: MovieMapper) : GeneralBoImpl() {

    /**
     * get movieList
     * PARAMS
     * view : generalView to respond generic errors
     */
    fun getMovieList(view: GeneralView, movieInterface: MovieInterface.getMoviesByName, page: String, textToSearch: String) {
        execute(tmdbRepo.getMoviesByName(page, textToSearch), object : GeneralSubscriber<MovieResponseDto, MovieResponse>(view) {
            override fun onSuccess(movies: MovieResponse) {
                movieInterface.getMoviesOk(movies)
            }

            override fun mapperResponseDto(t: MovieResponseDto): MovieResponse {
                return movieMapper.convertMovieResponseDtoToMovieResponse(t)
            }

            override fun isValid(t: MovieResponseDto): Boolean {
                return t.total_results > 0
            }
        })
    }
}