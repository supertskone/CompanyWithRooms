package com.pany.withrooms.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromDeveloperList(countryLang: List<Developer?>?): String? {
        val type = object : TypeToken<List<Developer?>?>() {}.type
        return Gson().toJson(countryLang, type)
    }

    @TypeConverter
    fun toDeveloperList(countryLangString: String?): List<Developer>? {
        val type = object : TypeToken<List<Developer?>?>() {}.type
        return Gson().fromJson<List<Developer>>(countryLangString, type)
    }

    @TypeConverter
    fun fromDetails(countryLang: Details?): String? {
        val type = object : TypeToken<Details>() {}.type
        return Gson().toJson(countryLang, type)
    }

    @TypeConverter
    fun toDetails(countryLangString: String?): Details? {
        val type = object : TypeToken<Details>() {}.type
        return Gson().fromJson<Details>(countryLangString, type)
    }

    @TypeConverter
    fun stringListToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToStringList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

}