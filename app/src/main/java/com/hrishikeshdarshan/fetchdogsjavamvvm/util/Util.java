package com.hrishikeshdarshan.fetchdogsjavamvvm.util;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hrishikeshdarshan.fetchdogsjavamvvm.R;

public class Util {

    public static void loadImage(ImageView imageView, String url){

        CircularProgressDrawable cpd = new CircularProgressDrawable(imageView.getContext());
        cpd.setStrokeWidth(10f);
        cpd.setCenterRadius(50f);
        cpd.start();

        RequestOptions options = new RequestOptions().placeholder(cpd).error(R.mipmap.ic_launcher);

        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(options)
                .load(url)
                .into(imageView);

    }

    @BindingAdapter("android:imageUrl")
    public static void loadImageb(ImageView view, String url){
        loadImage(view,url);
    }

}
