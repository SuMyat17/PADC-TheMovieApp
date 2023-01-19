package com.padcmyanmar.smtz.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.smtz.themovieapp.data.vos.ProductionCompanyVO

class ProductionCompanyTypeConverter {

    @TypeConverter
    fun toString(productionCompanies : List<ProductionCompanyVO>?) : String{
        return Gson().toJson(productionCompanies)
    }
    @TypeConverter
    fun toProductionCompanies(productionCompaniesJsonString: String) : List<ProductionCompanyVO>? {
        val productionCompaniesListType = object : TypeToken<List<ProductionCompanyVO>?>() {}.type
        return Gson().fromJson(productionCompaniesJsonString,productionCompaniesListType)
    }
}
