package com.example.lesze.myapplication.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.lesze.myapplication.model.Shop;

import java.util.ArrayList;
import java.util.List;

import static com.example.lesze.myapplication.database.ConstantStrings.DB_CREATE_TABLE;
import static com.example.lesze.myapplication.database.ConstantStrings.DB_NAME;
import static com.example.lesze.myapplication.database.ConstantStrings.DB_VERSION;
import static com.example.lesze.myapplication.database.ConstantStrings.DEBUG_TAG;
import static com.example.lesze.myapplication.database.ConstantStrings.DROP_DB_TABLE;
import static com.example.lesze.myapplication.database.ConstantStrings.ID_COLUMN;
import static com.example.lesze.myapplication.database.ConstantStrings.KEY_ID;
import static com.example.lesze.myapplication.database.ConstantStrings.KEY_NAME;
import static com.example.lesze.myapplication.database.ConstantStrings.KEY_PRICE;
import static com.example.lesze.myapplication.database.ConstantStrings.KEY_QUANTITY;
import static com.example.lesze.myapplication.database.ConstantStrings.NAME_COLUMN;
import static com.example.lesze.myapplication.database.ConstantStrings.PRICE_COLUMN;
import static com.example.lesze.myapplication.database.ConstantStrings.QUANTITY_COLUMN;
import static com.example.lesze.myapplication.database.ConstantStrings.TABLE_NAME;

/**
 * Created by lesze on 19.11.2017.
 */

public class MySqliteDatabase extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    public MySqliteDatabase(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE_TABLE);

        Log.d(DEBUG_TAG, "Database creating...");
        Log.d(DEBUG_TAG, "Table " + TABLE_NAME + " ver." + DB_VERSION + " created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_DB_TABLE);

        Log.d(DEBUG_TAG, "Database updating...");
        Log.d(DEBUG_TAG, "Table " + TABLE_NAME +
                " updated from ver." + oldVersion + " to ver." + newVersion);
        Log.d(DEBUG_TAG, "All data is lost.");

        onCreate(db);
    }

    public void addProducts(Shop shop) {
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, shop.getNazwa());
        values.put(KEY_PRICE, shop.getCena());
        values.put(KEY_QUANTITY, shop.getIlosc());

        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    public Shop getProduct(int id) {
        db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                new String[]{KEY_ID, KEY_NAME, KEY_PRICE, KEY_QUANTITY}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Shop shop = new Shop(Integer.parseInt(cursor.getString(ID_COLUMN)),
                cursor.getString(NAME_COLUMN),
                Integer.parseInt(cursor.getString(PRICE_COLUMN)),
                Integer.parseInt(cursor.getString(QUANTITY_COLUMN)));
        return shop;
    }

    public List<Shop> getAllShopProducts() {
        db = this.getReadableDatabase();
        List<Shop> shoppingList = new ArrayList<>();

        String selectAll = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst()) {
            do {
                Shop shop = new Shop();
                shop.setId(Integer.parseInt(cursor.getString(ID_COLUMN)));
                shop.setNazwa(cursor.getString(NAME_COLUMN));
                shop.setCena(Double.parseDouble(cursor.getString(PRICE_COLUMN)));
                shop.setIlosc(Double.parseDouble(cursor.getString(QUANTITY_COLUMN)));

//                    shop.setCena(Double.doubleToRawLongBits(cursor.getDouble(QUANTITY_COLUMN)));
//                    shop.setIlosc(Double.doubleToRawLongBits(cursor.getDouble(QUANTITY_COLUMN)));

                shoppingList.add(shop);
            }
            while (cursor.moveToNext());
        }

        return shoppingList;
    }

    public int updateProduct(Shop shop) {

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, shop.getNazwa());
        values.put(KEY_PRICE, shop.getCena());
        values.put(KEY_QUANTITY, shop.getIlosc());

        return db.update(TABLE_NAME, values, KEY_ID + "=?",
                new String[]{String.valueOf(shop.getId())});

    }

    public void deleteProduct(Shop shop) {

        db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + "=?",
                new String[]{String.valueOf(shop.getId())});

    }

    public void deleteProduct(int id) {

        db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + "=?",
                new String[]{String.valueOf(id)});
    }

    public int getProductsCount() {
        String count = "Select * from " + TABLE_NAME;

        db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(count, null);
        return cursor.getCount();

    }
}