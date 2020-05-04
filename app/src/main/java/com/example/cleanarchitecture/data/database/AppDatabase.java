package com.example.cleanarchitecture.data.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.cleanarchitecture.data.database.converters.CategoryListConverter;
import com.example.cleanarchitecture.data.entity.ReportEntity;


@Database(entities = {ReportEntity.class}, version = 1, exportSchema = false)
@TypeConverters({CategoryListConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "app-db";

    public abstract ReportDao reportDao();
}
