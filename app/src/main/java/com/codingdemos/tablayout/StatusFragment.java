package com.codingdemos.tablayout;


import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatusFragment extends Fragment {
    //Переменная для работы с БД
    private DBhelper mDBHelper;
    private SQLiteDatabase mDb;
    private static final String TAG = "MainActivity";
    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<Product> productList = new ArrayList<Product>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mDBHelper = new DBhelper(getActivity());
        try
        {
            mDBHelper.updateDataBase();
        }
        catch (IOException mIOException)
        {
            throw new Error("UnableToUpdateDatabase");
        }
        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException)
        {
            throw mSQLException;
        }
        View rootView = inflater.inflate(R.layout.fragment_status, container, false);
        // 1. get a reference to recyclerView
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.newrec);
        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Cursor cursor = mDb.rawQuery("SELECT * FROM foods", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            String Name = cursor.getString(2);
            Double car = Double.parseDouble(cursor.getString(3));
            Double fat = Double.parseDouble(cursor.getString(4));
            Double prot = Double.parseDouble(cursor.getString(4));
            Product temp = new Product(Name, car, fat, prot);
            productList.add(temp);
            mImageUrls.add("http://amforaoil.ru/wa-data/public/shop/products/73/00/73/images/74/74.970x0.jpg");

            cursor.moveToNext();
        }
        cursor.close();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mImageUrls,productList);

        recyclerView.setAdapter(adapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_status, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_status) {
            Toast.makeText(getActivity(), "Clicked on " + item.getTitle(), Toast.LENGTH_SHORT)
                    .show();
        }
        return true;
    }


}