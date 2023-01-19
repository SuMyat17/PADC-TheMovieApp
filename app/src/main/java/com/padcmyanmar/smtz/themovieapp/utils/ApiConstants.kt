package com.padcmyanmar.smtz.themovieapp.utils

const val BASE_URL = "https://api.themoviedb.org"
const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w400/";

const val API_GET_NOW_PLAYING = "/3/movie/now_playing";                //Banner
const val API_GET_POPULAR_MOVIES = "/3/movie/popular";                 //Best Popular and By Genres
const val API_GET_TOP_RATED_MOVIES = "/3/movie/top_rated";             //Showcases
const val API_GET_GENRES = "/3/genre/movie/list";
const val API_GET_MOVIES_BY_GENRE = "/3/discover/movie";
const val API_GET_ACTORS = "/3/person/popular"
const val API_GET_MOVIE_DETAILS = "/3/movie"
const val API_GET_CREDITS_BY_MOVIES = "/3/movie"
const val API_SEARCH_MOVIE = "/3/search/movie"

///params
const val PARAM_API_KEY = "api_key"
const val PARAM_PAGE = "page"
const val PARAM_GENRE_ID = "with_genres"
const val PARAM_QUERY = "query"

const val MOVIE_API_KEY = "6674ccf1b2849319f94cff83acc5b54d";