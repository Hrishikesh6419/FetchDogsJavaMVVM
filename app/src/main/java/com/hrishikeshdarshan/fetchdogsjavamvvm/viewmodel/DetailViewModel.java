package com.hrishikeshdarshan.fetchdogsjavamvvm.viewmodel;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hrishikeshdarshan.fetchdogsjavamvvm.model.DogBreed;
import com.hrishikeshdarshan.fetchdogsjavamvvm.model.DogDao;
import com.hrishikeshdarshan.fetchdogsjavamvvm.model.DogDatabase;

import java.util.List;
import java.util.UUID;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DetailViewModel extends AndroidViewModel {
    private RetrieveDogBreed task;

    public MutableLiveData<DogBreed> dogs = new MutableLiveData<>();

    public DetailViewModel(@NonNull Application application) {
        super(application);
    }


    public void fetch(int dogUuid){

        task = new RetrieveDogBreed();
        task.execute(dogUuid);


    }

    private class RetrieveDogBreed extends AsyncTask<Integer, Void, DogBreed>{


        @Override
        protected DogBreed doInBackground(Integer... integers) {
            int uuid = integers[0];

            return DogDatabase.getInstance(getApplication()).dogDao().getDog(uuid);


        }

        @Override
        protected void onPostExecute(DogBreed dogBreed) {
            dogs.setValue(dogBreed);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if(task != null){

            task.cancel(true);
            task = null;

        }

    }
}
