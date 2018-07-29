package com.example.alan_pc.androidmoviedb.data.entity

/**
 * Created by ALAN-PC on 29/07/2018
 */
class MovieResponseDto(var page: Int, var results:List<MovieDto>, var total_results: Int, var total_pages: Int) {
}