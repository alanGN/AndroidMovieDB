package com.example.alan_pc.androidmoviedb.data.entity

/**
 * Created by ALAN-PC on 29/07/2018
 */
class MovieDto(var poster_path: String, var adult: Boolean, var overview: String, var release_date: String, var genre_ids: List<Int>, var id: Int, var original_title: String,
               var original_language: String, var title: String, var backdrop_path: String, var popularity: Number, var vote_count: Int, var video: Boolean, var vote_average: Number) {
}