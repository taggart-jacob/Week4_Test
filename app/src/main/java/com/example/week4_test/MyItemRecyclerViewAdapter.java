package com.example.week4_test;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.week4_test.CoffeeListFragment.OnListFragmentInteractionListener;

import java.util.List;


public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<CoffeeItem> mValues;

    public MyItemRecyclerViewAdapter(List<CoffeeItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mListCoffeeName.setText(mValues.get(position).getName());
        holder.mListCoffeeDesc.setText(mValues.get(position).getDesc());
        holder.mListCoffeeImg.setText(mValues.get(position).getImageUrl());


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView mListCoffeeName;
        TextView mListCoffeeDesc;
        TextView mListCoffeeImg;
        public CoffeeItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mListCoffeeName = view.findViewById(R.id.tvListName);
            mListCoffeeDesc= view.findViewById(R.id.tvListDesc);
            mListCoffeeImg = view.findViewById(R.id.imgList);
        }

    }
}
