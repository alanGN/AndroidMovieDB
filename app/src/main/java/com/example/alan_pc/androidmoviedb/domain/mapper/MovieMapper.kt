package com.example.alan_pc.androidmoviedb.domain.mapper

import com.example.alan_pc.androidmoviedb.data.entity.MovieResponseDto
import com.example.alan_pc.androidmoviedb.domain.business.tmdb.Movie
import com.example.alan_pc.androidmoviedb.domain.business.tmdb.MovieResponse
import javax.inject.Inject

/**
 * Created by ALAN-PC on 29/07/2018
 */
class MovieMapper @Inject constructor() {

    fun convertMovieResponseDtoToMovieResponse(movieResponseDto: MovieResponseDto): MovieResponse {
        val movieList = mutableListOf<Movie>()

        movieResponseDto.results.forEach {
            val movie = Movie(it.poster_path, it.adult, it.overview, it.release_date, it.genre_ids, it.id, it.original_title, it.original_language, it.title, it.backdrop_path, it.popularity, it.vote_count, it.video, it.vote_average)
            movieList.add(movie)
        }

        return MovieResponse(movieList, movieResponseDto.page)
    }
}