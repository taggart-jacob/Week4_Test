package com.example.week4_test;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoffeeListFragment extends Fragment {


    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    RecyclerView recyclerView;
    ArrayList<CoffeeItem> coffeeList;


    public CoffeeListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CoffeeListFragment newInstance() {
        CoffeeListFragment fragment = new CoffeeListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("TAG", "WHATS WRONG");
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        DatabaseHelper dbHelper = new DatabaseHelper(getContext());

        if (dbHelper.queryForAllCoffeeRecords()!=null){
            coffeeList = dbHelper.queryForAllCoffeeRecords();
        } else {
            RetrofitCoffee retrofitCoffee = new RetrofitCoffee();
            retrofitCoffee.getService().getCoffeeItem("coffees").enqueue(new Callback<CoffeeItem>() {
                @Override
                public void onResponse(Call<CoffeeItem> call, Response<CoffeeItem> response) {
                    CoffeeItem coffeeItem = response.body();
                    dbHelper.insertCoffee(coffeeItem);
                    coffeeList.add(coffeeItem);
                    Log.d("TAG", "WHATS WRONG");

                }

                @Override
                public void onFailure(Call<CoffeeItem> call, Throwable t) {
                    Log.e("TAG", t.getMessage());
                }

            });

        }
        Log.d("TAG", coffeeList.toString());
        populateRecyclerView(coffeeList);

        // Set the adapter
        /*if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(coffeeList));
        }*/
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void populateRecyclerView(List<CoffeeItem> coffeeItems){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        MyItemRecyclerViewAdapter myRecyclerView = new MyItemRecyclerViewAdapter(coffeeItems);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myRecyclerView);
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(CoffeeItem item);
    }
}
