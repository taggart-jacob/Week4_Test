package com.example.week4_test;

import java.util.Locale;

public class DatabaseContract {

    public static final String DATABASE_NAME = "db_database_name";
    public static final String TABLE_NAME = "coffee_table";
    public static final String FIELD_DESC = "desc";
    public static final String FIELD_COFFEE_NAME = "coffee_name";
    public static final String FIELD_ID = "coffee_id";
    public static final String FIELD_IMAGE = "image";
    public static final int DATABASE_VERSION = 1;

    public static String getCreateTableStatement(){
        return String.format
                (Locale.US, "CREATE TABLE %s(%s TEXT PRIMARY_KEY, %s TEXT, %s TEXT, %s TEXT)",
                        TABLE_NAME,FIELD_ID, FIELD_COFFEE_NAME, FIELD_DESC, FIELD_IMAGE);
    }

    public static String getSelectOneCoffeeItem(String strId){
        return String.format(Locale.US, "SELECT * FROM %s WHERE %s = \"%s\"", TABLE_NAME, FIELD_ID, strId);
    }

    public static String getSelectAllCoffeeItems(){
        return String.format(Locale.US, "SELECT * FROM %s", TABLE_NAME);
    }
}
