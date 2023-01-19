package com.padcmyanmar.smtz.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreIdsTypeConverters {

    @TypeConverter
    fun toString(collection : List<Int>?) : String{
        return Gson().toJson(collection)
    }
    @TypeConverter
    fun toGenreIds(genreListJsonString: String) : List<Int>? {
        val genreIdsListType = object : TypeToken<List<Int>?>() {}.type
        return Gson().fromJson(genreListJsonString,genreIdsListType)
    }
}