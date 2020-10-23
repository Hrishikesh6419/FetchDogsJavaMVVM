package com.hrishikeshdarshan.fetchdogsjavamvvm.view;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.palette.graphics.Palette;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.hrishikeshdarshan.fetchdogsjavamvvm.R;
import com.hrishikeshdarshan.fetchdogsjavamvvm.databinding.FragmentDetailBinding;
import com.hrishikeshdarshan.fetchdogsjavamvvm.model.DogBreed;
import com.hrishikeshdarshan.fetchdogsjavamvvm.model.DogPalette;
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

                if(dogBreed.imageUrl != null){

                    setupBackgroundColor(dogBreed.imageUrl);

                }

            }
        });

    }

    private void setupBackgroundColor(String url){

        Glide.with(this)
                .asBitmap()
                .load(url)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {

                        Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(@Nullable Palette palette) {

                                int intColor = palette.getLightMutedSwatch().getRgb();
                                DogPalette myPalette = new DogPalette(intColor);
                                binding.setPalette(myPalette);
                            }
                        });
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });

    }

}