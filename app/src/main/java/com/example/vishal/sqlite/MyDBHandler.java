package com.example.vishal.sqlite;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "products.db";
    public static final String TABLE_PRODUCTS ="products";
    public static final String COLUME_ID ="_id";
    public static final String COLUME_PRODUCTNAME ="_productname";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE" + TABLE_PRODUCTS + "(" +
                COLUME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " +
                COLUME_PRODUCTNAME + " TEXT " +
                ");";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_PRODUCTS);

    }

    //Add a new row to the database
    public void addProduct(Products product){
        ContentValues values = new ContentValues();
        values.put(COLUME_PRODUCTNAME, product,get_productname());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    //Delete a product from the database
    public void deleteProduct (Products product);
    SQLiteDatabase db = getWritableDatabase();

}
