package com.hrishikeshdarshan.fetchdogsjavamvvm.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import static com.hrishikeshdarshan.fetchdogsjavamvvm.view.ListFragmentDirections.*;

public class ListFragment extends Fragment {
//
//    @BindView(R.id.floatingActionButton)
//    FloatingActionButton fab;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

//   det

//    void onGoToDeatils(){
////
////        ListFragmentDirections.ActionDetail action = ListFragmentDirections.actionDetail();
//
//
//        ActionDetail action = actionDetail();
//        action.setDogUuid(5);
//
//
//        Navigation.findNavController(fab).navigate(action);
//
//
//    }

}