package com.example.week4_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements CoffeeListFragment.OnListFragmentInteractionListener{

    FragmentManager fragmentManager;
    CoffeeListFragment coffeeListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        coffeeListFragment = CoffeeListFragment.newInstance();

        fragmentManager.beginTransaction().add(R.id.frmHolderCoffeeList, coffeeListFragment).commit();
    }

    @Override
    public void onListFragmentInteraction(CoffeeItem item) {

    }
}
