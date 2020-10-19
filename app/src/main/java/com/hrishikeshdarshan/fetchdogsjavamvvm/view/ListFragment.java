package com.hrishikeshdarshan.fetchdogsjavamvvm.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hrishikeshdarshan.fetchdogsjavamvvm.R;
import com.hrishikeshdarshan.fetchdogsjavamvvm.model.DogBreed;
import com.hrishikeshdarshan.fetchdogsjavamvvm.viewmodel.ListViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.hrishikeshdarshan.fetchdogsjavamvvm.view.ListFragmentDirections.*;

public class ListFragment extends Fragment {

    private ListViewModel viewModel;
    private DogListAdapter dogListAdapter = new DogListAdapter(new ArrayList<>());

    @BindView(R.id.dogsList)
    RecyclerView dogsList;

    @BindView(R.id.listError)
    TextView listError;

    @BindView(R.id.loadingView)
    ProgressBar loadingView;

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;



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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ActionDetail action = ListFragmentDirections.actionDetail();
        Navigation.findNavController(view).navigate(action);

        viewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        viewModel.refresh();

        dogsList.setLayoutManager(new LinearLayoutManager(getContext()));
        dogsList.setAdapter(dogListAdapter);

        observeViewModel();
    }

    private void observeViewModel() {

        viewModel.dogs.observe(this, dogs -> {
            if(dogs != null){
                dogsList.setVisibility(View.VISIBLE);
                dogListAdapter.updateDogsList(dogs);
            }
        });

        viewModel.dogLoadError.observe(this, isError -> {

            if(isError != null){

                listError.setVisibility(isError? View.VISIBLE: View.GONE);

            }

        });

        viewModel.loading.observe(this, isLoading -> {
            if(isLoading != null){
                loadingView.setVisibility(isLoading? View.VISIBLE: View.GONE);
                if(isLoading){
                    listError.setVisibility(View.GONE);
                    dogsList.setVisibility(View.GONE);
                }
            }
        });
    }

}