//package com.padcmyanmar.smtz.themovieapp.network.dataagents
//
//import android.os.AsyncTask
//import android.util.Log
//import com.google.gson.Gson
//import com.padcmyanmar.smtz.themovieapp.data.vos.MovieVO
//import com.padcmyanmar.smtz.themovieapp.network.responses.MovieListResponse
//import com.padcmyanmar.smtz.themovieapp.utils.API_GET_NOW_PLAYING
//import com.padcmyanmar.smtz.themovieapp.utils.BASE_URL
//import com.padcmyanmar.smtz.themovieapp.utils.MOVIE_API_KEY
//import java.io.BufferedReader
//import java.io.InputStreamReader
//import java.lang.Exception
//import java.lang.StringBuilder
//import java.net.HttpURLConnection
//import java.net.URL
//
//object MovieDataAgentImpl : MovieDataAgent {
//    override fun getNowPlayingMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        GetNowPlayingMovieTask().execute()
//    }
//
//    class GetNowPlayingMovieTask() : AsyncTask<Void, Void, MovieListResponse?>() {
//        //doInBackground -> background thread
//        override fun doInBackground(vararg params: Void?): MovieListResponse? {
//            val url : URL
//            var reader : BufferedReader? = null
//            val stringBuilder : StringBuilder
//
//            try{
//                url = URL("""$BASE_URL$API_GET_NOW_PLAYING?api_key=$MOVIE_API_KEY&language=en-US&page=1""")  //1
//
//                val connection = url.openConnection() as HttpURLConnection                                       //2
//
//                //set HTTP method
//                connection.requestMethod = "GET"             //3
//
//                //give it 15 second to respond
//                connection.readTimeout = 15 * 1000           //4
//
//                //
//                connection.doInput = true                    //5
//                connection.doOutput = false                                          //request yae body ka nay data ko pay tr ma hoke loh , URL ka nay pay tr moh loh false htr loh ya
//
//                connection.connect()                         //7
//
//
//                //read the output from the server
//                reader = BufferedReader(
//                    InputStreamReader(connection.inputStream)
//                )
//                //8
//                stringBuilder = StringBuilder()
//
//                for(line in reader.readLines()){
//                    stringBuilder.append(line + "\n")
//                }
//
//                val responseString = stringBuilder.toString()
//                Log.d("NowPlayingMovies", responseString)
//
//                val movieListResponse = Gson().fromJson(responseString, MovieListResponse::class.java)
//
//                return movieListResponse
//
//            } catch (e: Exception){
//                e.printStackTrace()
//                Log.e("NewsError", e.message ?: "")
//            } finally {
//                if (reader != null){
//                    try{
//                        reader.close()
//                    } catch (ioe: Exception){
//                        ioe.printStackTrace()
//                    }
//                }
//            }
//            return null
//        }
//
//
//
//        //onPostExecute -> main thread
//        override fun onPostExecute(result: MovieListResponse?) {
//            super.onPostExecute(result)
//        }
//
//    }
//}