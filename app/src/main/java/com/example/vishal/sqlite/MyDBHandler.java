package com.example.vishal.sqlite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "products.db"; // Name of database
    private static final int DATABASE_VERSION = 1; // Database version
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCTNAME = "productname";



    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){

        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db) {
        // create statement for making the table - the table is called inventory
        String DATABASE_CREATE = "CREATE TABLE " + TABLE_PRODUCTS +"("+
                COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_PRODUCTNAME +" TEXT " +
                ");";

        db.execSQL(DATABASE_CREATE);

    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS products");
        onCreate(db);
    }

    public void addProduct(Products product){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.get_productname());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    public void deleteProduct(String productName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + "=\"" + productName + "\";");
        db.close();
    }

    public String databasetoString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM '" + TABLE_PRODUCTS + "' WHERE 1;";

        //CURSOR WILL POINT TO THE LOCTION IN THE RESULTS
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("productname"))!=null){
                dbString += c.getString(c.getColumnIndex("productname"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }

}


