package com.padcmyanmar.smtz.themovieapp.data.models

import androidx.lifecycle.LiveData
import com.padcmyanmar.smtz.themovieapp.data.vos.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

object MovieModelImpl : MovieModel, BaseModel() {

    //Data Agent
    //private val mMovieDataAgent: MovieDataAgent = RetrofitDataAgentImpl

//    fun initDatabase(context: Context) {
//        mMovieDatabase = MovieDatabase.getDBInstance(context)
//    }

    override fun getNowPlayingMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>? {
        //Database
//        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = NOW_PLAYING) ?: listOf())

        //Network
        mTheMovieApi.getNowPlayingMovies(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                it.results?.forEach { movie -> movie.type = NOW_PLAYING }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())
//                onSuccess(it.results ?: listOf())
            },
            {
                onFailure(it.localizedMessage ?: "")                              // { } error event
            })
        return mMovieDatabase?.movieDao()?.getMoviesByType(type = NOW_PLAYING)

        //Data Agent
//        mMovieDataAgent.getNowPlayingMovies(onSuccess = {
//            it.forEach { movie -> movie.type = NOW_PLAYING }
//            mMovieDatabase?.movieDao()?.insertMovies(it)

//            onSuccess(it)
//        }, onFailure = onFailure)
    }

    override fun getPopularMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>? {

//        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = POPULAR) ?: listOf())

        mTheMovieApi.getPopularMovies(page=1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach { movie -> movie.type = POPULAR }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())
//                onSuccess(it.results ?: listOf())
            },{
                onFailure(it.localizedMessage ?: "")
            })
        return mMovieDatabase?.movieDao()?.getMoviesByType(type = POPULAR)
    }

    override fun getTopRatedMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>? {
//        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = TOP_RATED) ?: listOf())

        mTheMovieApi.getTopRatedMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach { movie -> movie.type = TOP_RATED }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())
//                onSuccess(it.results ?: listOf())
            },{
                onFailure(it.localizedMessage ?: "")
            })
        return mMovieDatabase?.movieDao()?.getMoviesByType(type = TOP_RATED)
    }

    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {

        //Data Agent
//        mMovieDataAgent.getGenres(onSuccess = onSuccess, onFailure = onFailure)

        mTheMovieApi.getGenres()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.genres ?: listOf())
            },{
                onFailure(it.localizedMessage ?: "")
            })
    }

    override fun getMoviesByGenre(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        //Data Agent
//        mMovieDataAgent.getMoviesByGenre(genreId = genreId, onSuccess = onSuccess, onFailure = onFailure)

        mTheMovieApi.getMoviesByGenre(genreId = genreId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.results ?: listOf())
            },{
                onFailure(it.localizedMessage ?: "")
            })
    }

    override fun getActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {

        //Data Agent
//        mMovieDataAgent.getActors(onSuccess = onSuccess, onFailure = onFailure)

        mTheMovieApi.getActors()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.results ?: listOf())
            },{
                onFailure(it.localizedMessage ?: "")
            })
    }

    override fun getMovieDetails(
        movieId: String,
//        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ): LiveData<MovieVO?>? {

        //Database
//        val movieFromDatabase = mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
//        Log.d("movieFromDatabase", movieFromDatabase.toString())
//        movieFromDatabase?.let {
//            onSuccess(it)
//        }

        //Network
        mTheMovieApi.getMovieDetails(movieId = movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val movieFromDatabaseToSync = mMovieDatabase?.movieDao()?.getMovieByIdOneTime(movieId.toInt())
                it.type = movieFromDatabaseToSync?.type

                mMovieDatabase?.movieDao()?.insertSingleMovie(it)

//                onSuccess(it)
            },{
                onFailure(it.localizedMessage ?: "")
            })
        return mMovieDatabase?.movieDao()?.getMovieById(movieId.toInt())

        //Data Agent
//        mMovieDataAgent.getMovieDetails(
//            movieId = movieId,
//            onSuccess = {
//                val movieFromDatabase =
//                    mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
//                it.type = movieFromDatabase?.type.toString()
//
//                mMovieDatabase?.movieDao()?.insertSingleMovie(it)
//                onSuccess(it)
//            },
//            onFailure = onFailure
//        )
    }

    override fun getCreditsByMovies(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi.getCreditsByMovies(movieId = movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(Pair(it.cast ?: listOf(), it.crew ?: listOf()))
            },{
                onFailure(it.localizedMessage ?: "")
            })

        //Data Agent
//        mMovieDataAgent.getCreditsByMovies(movieId, onSuccess, onFailure)
    }

    override fun searchMovies(
        query: String
    ) : Observable<List<MovieVO>>{
        return mTheMovieApi
            .searchMovies(query = query)
            .map { it.results ?: listOf() }
            .onErrorResumeNext { Observable.just(listOf()) }
            .subscribeOn(Schedulers.io())
    }

}