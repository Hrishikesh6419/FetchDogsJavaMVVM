package com.hrishikeshdarshan.fetchdogsjavamvvm.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.hrishikeshdarshan.fetchdogsjavamvvm.R;
import com.hrishikeshdarshan.fetchdogsjavamvvm.databinding.ItemDogBinding;
import com.hrishikeshdarshan.fetchdogsjavamvvm.model.DogBreed;
import com.hrishikeshdarshan.fetchdogsjavamvvm.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DogListAdapter extends RecyclerView.Adapter<DogListAdapter.DogViewHolder> implements DogClickListener{

    private ArrayList<DogBreed> dogsList;


    public DogListAdapter(ArrayList<DogBreed> dogsList, Context context) {
        this.dogsList = dogsList;

    }

    public void updateDogsList(List<DogBreed> newDogsList){
        dogsList.clear();
        dogsList.addAll(newDogsList);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemDogBinding view = DataBindingUtil.inflate(inflater, R.layout.item_dog, parent, false);
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dog, parent,false );

        return new DogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {

        holder.itemView.setDog(dogsList.get(position));
        holder.itemView.setListener(this);
    }

    @Override
    public int getItemCount() {
        return dogsList.size();
    }

    @Override
    public void onDogClicked(View v) {

        String uuidString = ((TextView)v.findViewById(R.id.dogId)).getText().toString();
        int uuid = Integer.parseInt(uuidString);

        ListFragmentDirections.ActionDetail action = ListFragmentDirections.actionDetail();
        action.setDogUuid(uuid);
        Navigation.findNavController(v).navigate(action);


    }

    class DogViewHolder extends RecyclerView.ViewHolder{

        public ItemDogBinding itemView;

        public DogViewHolder(@NonNull ItemDogBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }

}
