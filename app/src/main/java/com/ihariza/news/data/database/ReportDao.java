package com.ihariza.news.data.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ihariza.news.data.entity.ReportEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;


@Dao
public interface ReportDao {

    @Query("SELECT * FROM report")
    Single<List<ReportEntity>> getAll();

    @Query("SELECT * FROM report where page LIKE :pageNumber")
    Single<List<ReportEntity>> getAll(int pageNumber);

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
