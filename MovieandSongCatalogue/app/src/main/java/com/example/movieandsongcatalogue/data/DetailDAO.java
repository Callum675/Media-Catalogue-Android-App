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

    //add single
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Detail detail);

    //add multiple
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Detail> detail);

    //delete single
    @Delete
    void delete(Detail detail);

    //delete by name
    @Query("DELETE FROM Detail WHERE name = :Name")
    void deleteByName(String Name);

    //delete multiple
    @Delete
    void delete(List<Detail> detail);

    //query based on name of media
    @Query("SELECT * FROM Detail WHERE name = :Name")
    List<Detail> findByName(String Name);

    //get all data
    @Query("SELECT * FROM Detail")
    List<Detail> getAll();
}
