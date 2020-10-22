package com.hrishikeshdarshan.fetchdogsjavamvvm.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DogDao {

    @Insert
    //List<Integer> insertAll(DogBreed... dogs);
    List<Long> insertAll(List<DogBreed> dogs);

    @Query("SELECT * FROM dogbreed")
    List<DogBreed> getAllDogs();

    @Query("SELECT * FROM dogbreed WHERE uuid = :dogId")
    DogBreed getDog(int dogId);

    @Query("DELETE FROM DogBreed")
    void deleteAllDogs();

}
