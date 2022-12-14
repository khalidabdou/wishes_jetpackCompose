package com.wishes.jetpackcompose.data

import androidx.room.TypeConverter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wishes.jetpackcompose.data.entities.Categories
import com.wishes.jetpackcompose.data.entities.Image


class imageTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun imageToString(image: Image): String {
        return gson.toJson(image)
    }

    @TypeConverter
    fun StringToImage(data: String): Image {
        var listType = object : TypeToken<Image>() {}.type
        return gson.fromJson(data, listType)
    }


    @TypeConverter
    fun CategoryToString(categories: Categories): String {
        return gson.toJson(categories)
    }

    @TypeConverter
    fun StringToCategory(data: String): Categories {
        var listType = object : TypeToken<Categories>() {}.type
        return gson.fromJson(data, listType)
    }


}