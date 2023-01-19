package com.padcmyanmar.smtz.themovieapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.padcmyanmar.smtz.themovieapp.persistence.typeconverters.*

@Entity(tableName = "movies")
@TypeConverters(
    CollectionTypeConverter::class,
    GenreIdsTypeConverters::class,
    GenreListTypeConverter::class,
    ProductionCountryTypeConverter::class,
    ProductionCompanyTypeConverter::class,
    SpokenLanguageTypeConverter::class,
)

data class MovieVO (
    @SerializedName("adult")
    @ColumnInfo(name = "adult")
    val adult : Boolean?,
    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    val backDropPath : String?,
    @SerializedName("genre_ids")
    @ColumnInfo(name = "genre_ids")
    val genreIds : List<Int>?,

    @SerializedName("belongs_to_collection")
    @ColumnInfo(name = "belongs_to_collection")
    val belongsToCollection : CollectionVO?,

    @SerializedName("id")
    @PrimaryKey
    val id : Int = 0,

    @SerializedName("original_language")
    @ColumnInfo(name = "original_language")
    val originalLanguage : String?,
    @SerializedName("original_title")
    @ColumnInfo(name = "original_title")
    val originalTitle : String?,
    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    val overview : String?,
    @SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    val popularity : Double?,
    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    val posterPath : String?,
    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    val releaseDate : String?,
    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title : String?,
    @SerializedName("video")
    @ColumnInfo(name = "video")
    val video : Boolean?,
    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    val voteAverage : Double?,
    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    val voteCount : Int?,

    @SerializedName("production_companies")
    @ColumnInfo(name = "production_companies")
    val productionCompanies : List<ProductionCompanyVO>?,
    @SerializedName("production_countries")
    @ColumnInfo(name = "production_countries")
    val productionCountry : List<ProductionCountryVO>?,
    @SerializedName("revenue")
    @ColumnInfo(name = "revenue")
    val revenue : Int?,
    @SerializedName("runtime")
    @ColumnInfo(name = "runtime")
    val runtime : Int?,
    @SerializedName("spoken_languages")
    @ColumnInfo(name = "spoken_languages")
    val spokenLanguage : List<SpokenLanguageVO>?,
    @SerializedName("status")
    @ColumnInfo(name = "status")
    val status : String?,
    @SerializedName("tagline")
    @ColumnInfo(name = "tagline")
    val tagline : String?,
    @SerializedName("genres")
    @ColumnInfo(name = "genres")
    val genres : List<GenreVO>?,

    @ColumnInfo(name = "type")
    var type: String?,

    ) {

    fun getRatingBasedOnFiveStars() : Float {
        return voteAverage?.div(2)?.toFloat() ?: 0.0f
    }

    fun getGenreAsCommaSeparatedString() : String {
        return genres?.map { it.name }?.joinToString(",") ?: ""
    }

    fun getProductionCountriesAsCommaSeparatedString() : String {
        return productionCountry?.map { it.name }?.joinToString(",") ?: ""
    }
}

const val NOW_PLAYING = "NOW PLAYING"
const val POPULAR = "POPULAR"
const val TOP_RATED = "TOP RATED"