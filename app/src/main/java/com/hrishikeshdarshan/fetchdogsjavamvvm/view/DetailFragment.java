package com.hrishikeshdarshan.fetchdogsjavamvvm.view;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hrishikeshdarshan.fetchdogsjavamvvm.R;
import com.hrishikeshdarshan.fetchdogsjavamvvm.databinding.FragmentDetailBinding;
import com.hrishikeshdarshan.fetchdogsjavamvvm.model.DogBreed;
import com.hrishikeshdarshan.fetchdogsjavamvvm.util.Util;
import com.hrishikeshdarshan.fetchdogsjavamvvm.viewmodel.DetailViewModel;


import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment {

    private int dogUuid;
    DetailViewModel viewModel;
    FragmentDetailBinding binding;




    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments() != null){
            dogUuid = DetailFragmentArgs.fromBundle(getArguments()).getDogUuid();
        }
        viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        viewModel.fetch(dogUuid);
        observe();

    }

    private void observe() {

        viewModel.dogs.observe(this, new Observer<DogBreed>() {
            @Override
            public void onChanged(DogBreed dogBreed) {

                binding.setDog(dogBreed);

            }
        });




    }
}