package com.example.lesze.myapplication.database;

/**
 * Created by lesze on 30.11.2017.
 */

public class ConstantStrings {

    public static final String DEBUG_TAG = "SqLiteManager";

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "ShopDatabase.db";
    public static final String TABLE_NAME = "shoppingList";

    public static final String KEY_ID = "id";
    public static final String ID_OPTIONS = "INTEGER PRIMARY KEY";
    public static final int ID_COLUMN = 0;
    public static final String KEY_NAME = "product";
    public static final String NAME_OPTIONS = "TEXT NOT NULL";
    public static final int NAME_COLUMN = 1;
    public static final String KEY_PRICE = "price";
    public static final String PRICE_OPTIONS = "DOUBLE DEFAULT 0";
    public static final int PRICE_COLUMN = 2;
    public static final String KEY_QUANTITY = "quantity";
    public static final String QUANTITY_OPTIONS = "DOUBLE DEFAULT 0";
    public static final int QUANTITY_COLUMN = 3;

    public static final String DROP_DB_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final String DB_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME +
                    "( " + KEY_ID + " " + ID_OPTIONS + ", " +
                    KEY_NAME + " " + NAME_OPTIONS + ", " +
                    KEY_PRICE + " " + PRICE_OPTIONS + ", " +
                    KEY_QUANTITY + " " + QUANTITY_OPTIONS +
                    ");";

}
