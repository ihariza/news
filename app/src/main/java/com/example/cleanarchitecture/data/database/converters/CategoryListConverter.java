package com.example.cleanarchitecture.data.database.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class CategoryListConverter {

    private CategoryListConverter() {
        throw new IllegalStateException("Converter class");
    }

    @TypeConverter
    public static String fromCategoryList(List<String> categories) {
        if (categories == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        return gson.toJson(categories, type);
    }

    @TypeConverter
    public static List<String> toCategoryList(String category) {
        if (category == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        return gson.fromJson(category, type);
    }
}
