package com.hrishikeshdarshan.fetchdogsjavamvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hrishikeshdarshan.fetchdogsjavamvvm.model.DogBreed;

import java.util.List;

public class DetailViewModel extends ViewModel {

    public MutableLiveData<DogBreed> dogs = new MutableLiveData<>();


    public void fetch(){
        dogs.setValue(new DogBreed("1", "Lba", "15", "e", "e", "friendly", ""));
    }


}
