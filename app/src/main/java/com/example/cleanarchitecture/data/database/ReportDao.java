package com.example.cleanarchitecture.data.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.cleanarchitecture.data.entity.ReportEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;


@Dao
public interface ReportDao {

    @Query("SELECT * FROM report")
    Single<List<ReportEntity>> getAll();

    @Query("SELECT * FROM report where id LIKE :id")
    Single<ReportEntity> findBy(String id);

    @Query("SELECT COUNT(*) from report")
    int count();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(ReportEntity report);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(List<ReportEntity> news);

    @Delete
    Completable delete(ReportEntity report);

    @Query("DELETE FROM report")
    Completable deleteAll();
}
