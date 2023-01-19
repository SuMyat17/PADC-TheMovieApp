package com.padcmyanmar.smtz.themovieapp.mvp.views

import com.padcmyanmar.smtz.themovieapp.data.vos.ActorVO
import com.padcmyanmar.smtz.themovieapp.data.vos.GenreVO
import com.padcmyanmar.smtz.themovieapp.data.vos.MovieVO

//Presenter ka nay Activity ko khine mae func
interface MainView : BaseView {
    fun showNowPlayingMovies(nowPlayingMovies: List<MovieVO>)
    fun showPopularMovies(popularMovies: List<MovieVO>)
    fun showTopRatedMovies(topRatedMovies: List<MovieVO>)
    fun showGenres(genreList: List<GenreVO>)
    fun showMoviesByGenre(moviesByGenre: List<MovieVO>)
    fun showActors(actors: List<ActorVO>)
    fun navigateToMovieDetailsScreen(movieId: Int)
}