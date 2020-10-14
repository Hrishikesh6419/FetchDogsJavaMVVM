package com.hrishikeshdarshan.fetchdogsjavamvvm.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hrishikeshdarshan.fetchdogsjavamvvm.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment {

    @BindView(R.id.floatingActionButtonDetails)
    FloatingActionButton fab;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        ButterKnife.bind(this,view);

        fab.setOnClickListener(view1 -> goToList());


        return view;
    }

    private void goToList() {

        NavDirections action = DetailFragmentDirections.actionList();

        Navigation.findNavController(fab).navigate(action);




    }


}