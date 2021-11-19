package com.example.movieandsongcatalogue.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

//Data Access Object
@Dao
public interface DetailDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Detail detail);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(List<Detail> detail);

    @Delete
    public void delete(Detail detail);

    @Delete
    public void delete(List<Detail> detail);

    @Query("SELECT * FROM Detail WHERE name = :name")
    public List<Detail> findByName(String name);

    @Query("SELECT * FROM Detail")
    List<Detail> getAll();

}
