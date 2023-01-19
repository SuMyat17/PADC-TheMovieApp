package com.padcmyanmar.smtz.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.smtz.themovieapp.data.vos.ProductionCountryVO

class ProductionCountryTypeConverter {

    @TypeConverter
    fun toString(productionCountries : List<ProductionCountryVO>?) : String{
        return Gson().toJson(productionCountries)
    }
    @TypeConverter
    fun toProductionCountries(productionCountriesJsonString: String) : List<ProductionCountryVO>? {
        val productionCountriesListType = object : TypeToken<List<ProductionCountryVO>?>() {}.type
        return Gson().fromJson(productionCountriesJsonString,productionCountriesListType)
    }
}