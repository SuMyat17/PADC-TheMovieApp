package com.padcmyanmar.smtz.themovieapp.network

import com.padcmyanmar.smtz.themovieapp.data.vos.MovieVO
import com.padcmyanmar.smtz.themovieapp.network.responses.GetActorsResponse
import com.padcmyanmar.smtz.themovieapp.network.responses.GetCreditsByMoviesResponse
import com.padcmyanmar.smtz.themovieapp.network.responses.GetGenreResponse
import com.padcmyanmar.smtz.themovieapp.network.responses.MovieListResponse
import com.padcmyanmar.smtz.themovieapp.utils.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieApi {

    @GET(API_GET_NOW_PLAYING)
    fun getNowPlayingMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1
    ) : Observable<MovieListResponse>

    @GET(API_GET_POPULAR_MOVIES)
    fun getPopularMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1
    ) : Observable<MovieListResponse>

    @GET(API_GET_TOP_RATED_MOVIES)
    fun getTopRatedMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page : Int = 1
    ) : Observable<MovieListResponse>

    @GET(API_GET_GENRES)
    fun getGenres(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY
    ) : Observable<GetGenreResponse>

    @GET(API_GET_MOVIES_BY_GENRE)
    fun getMoviesByGenre(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_GENRE_ID) genreId : String                                            // NL mhr Query para,Path para doh ko String pl pay dl
    ) : Observable<MovieListResponse>

    @GET(API_GET_ACTORS)
    fun getActors(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page : Int = 1
    ) : Observable<GetActorsResponse>

    @GET("$API_GET_MOVIE_DETAILS/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId : String,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page : Int = 1
    ) : Observable<MovieVO>

    @GET("$API_GET_CREDITS_BY_MOVIES/{movie_id}/credits")
    fun getCreditsByMovies(
        @Path("movie_id") movieId: String,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page : Int = 1
    ) : Observable<GetCreditsByMoviesResponse>

    @GET(API_SEARCH_MOVIE)
    fun searchMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_QUERY) query: String
    ) : Observable<MovieListResponse>
}