package com.example.alan_pc.androidmoviedb.presentation.presenters

import com.example.alan_pc.androidmoviedb.data.entity.MovieDto
import com.example.alan_pc.androidmoviedb.data.entity.MovieResponseDto
import com.example.alan_pc.androidmoviedb.domain.business.tmdb.TmdbBo
import com.example.alan_pc.androidmoviedb.domain.business.tmdb.TmdbRepo
import com.example.alan_pc.androidmoviedb.domain.business.tmdb.interfaces.MovieInterface
import com.example.alan_pc.androidmoviedb.presentation.general.GeneralView
import com.example.alan_pc.androidmoviedb.presentation.home.HomeMvp
import com.example.alan_pc.androidmoviedb.presentation.home.HomePresenter
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


/**
 * Created by ALAN-PC on 31/07/2018
 */
class HomePresenterTest {

    @Mock
    private lateinit var tmdbBo: TmdbBo

    @Mock
    private lateinit var tmdbBoRepo: TmdbRepo

    @Mock
    private lateinit var view: GeneralView

    @Mock
    private lateinit var mockView: HomeMvp.View

    private lateinit var presenter: HomePresenter

    @Mock
    private lateinit var moviInterface: MovieInterface.getMoviesByName

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = HomePresenter(mockView, tmdbBo)
    }

    @Test
    fun testGetErrorMoviesByName() {
        val error = "Error"


        val observable2: Observable<MovieResponseDto> = Observable.create { e ->
            e.onError(Exception(error))
        }


        whenever(tmdbBoRepo.getMoviesByName("-1", "Batman")).thenReturn(observable2)
        presenter.getMoviesByName("-1", "Batman")

    }

    @Test
    fun testGetMoviesByName() {
        val movies = mutableListOf<MovieDto>(MovieDto("", true, "bla bla", "12291293", mutableListOf<Int>(1, 3), 1, "Batman", "Spanish",
                "Batman3", "", 2, 2, true, 2))

        val observable: Observable<MovieResponseDto> = Observable.create { e ->
            e.onNext(MovieResponseDto(1, movies, 1, 1))
        }
        whenever(tmdbBoRepo.getMoviesByName("1", "Batman")).thenReturn(observable)
        presenter.getMoviesByName("1", "Batman")
    }


}

