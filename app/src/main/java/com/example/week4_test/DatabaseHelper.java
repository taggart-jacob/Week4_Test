package com.example.week4_test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static com.example.week4_test.DatabaseContract.DATABASE_NAME;
import static com.example.week4_test.DatabaseContract.FIELD_COFFEE_NAME;
import static com.example.week4_test.DatabaseContract.FIELD_DESC;
import static com.example.week4_test.DatabaseContract.FIELD_ID;
import static com.example.week4_test.DatabaseContract.FIELD_IMAGE;
import static com.example.week4_test.DatabaseContract.DATABASE_VERSION;
import static com.example.week4_test.DatabaseContract.TABLE_NAME;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DatabaseContract.getCreateTableStatement());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //insert values into db
    public void insertCoffee(CoffeeItem coffeeInsert){
        //create content value which holds key value pairs, key
        //being the column in the db, and value being the value associated with that column
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_DESC, coffeeInsert.getDesc());
        contentValues.put(FIELD_COFFEE_NAME, coffeeInsert.getName());
        contentValues.put(FIELD_ID, coffeeInsert.getId());
        contentValues.put(FIELD_IMAGE, coffeeInsert.getImageUrl());

        //Need to get a writable db
        SQLiteDatabase writeableDatabase = this.getWritableDatabase();

        //insert into the database
        writeableDatabase.insert(TABLE_NAME, null, contentValues);
        writeableDatabase.close();
    }

    public CoffeeItem queryForOneCoffeeRecord(String strId){
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        CoffeeItem returnCoffee = null;

        Cursor cursor = readableDatabase.rawQuery(DatabaseContract.getSelectOneCoffeeItem(strId), null);

        if (cursor.moveToFirst()){
            String nameFromDB = cursor.getString(cursor.getColumnIndex(FIELD_COFFEE_NAME));
            String descFromDB = cursor.getString(cursor.getColumnIndex(FIELD_DESC));
            String idFromDB = cursor.getString(cursor.getColumnIndex(FIELD_ID));
            String imageFromDB = cursor.getString(cursor.getColumnIndex(FIELD_IMAGE));
            //tring desc, String imageUrl, String id, String name
            returnCoffee = new CoffeeItem(descFromDB, imageFromDB, idFromDB, nameFromDB);
        }

        readableDatabase.close();
        return returnCoffee;
    }

    public ArrayList<CoffeeItem> queryForAllCoffeeRecords(){
        ArrayList<CoffeeItem> returnCoffeeList = null;
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery(DatabaseContract.getSelectAllCoffeeItems(), null);

        if (cursor.moveToFirst()){
            returnCoffeeList = new ArrayList<>();
            do {
                CoffeeItem returnCoffee = null;
                String descFromDB = cursor.getString(cursor.getColumnIndex(FIELD_DESC));
                String imageFromDB = cursor.getString(cursor.getColumnIndex(FIELD_IMAGE));
                String idFromDB = cursor.getString(cursor.getColumnIndex(FIELD_ID));
                String nameFromDB = cursor.getString(cursor.getColumnIndex(FIELD_COFFEE_NAME));
                returnCoffee = new CoffeeItem(descFromDB, imageFromDB, idFromDB, nameFromDB);
                returnCoffeeList.add(returnCoffee);
            } while(cursor.moveToNext());
        }

        readableDatabase.close();
        return returnCoffeeList;
    }
}
