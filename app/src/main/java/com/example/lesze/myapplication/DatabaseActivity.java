package com.example.lesze.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lesze.myapplication.adapter.MyAdapter;
import com.example.lesze.myapplication.database.MySqliteDatabase;
import com.example.lesze.myapplication.model.Shop;

import java.util.ArrayList;
import java.util.List;

public class DatabaseActivity extends AppCompatActivity {

    AlertDialog.Builder dialogBuilder;
    AlertDialog dialog;
    EditText nameEdit, quantityEdit, priceEdit;
    Button saveButton, dismissButton;
    MySqliteDatabase db;
    private Shop shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

//        MyAdapter recyclerViewAdapter = new MyAdapter(RecyclerViewActivity.class, onCreate());
//        recyclerView.setAdapter(recyclerViewAdapter);
//        recyclerViewAdapter.notifyDataSetChanged();

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity
//                        (new Intent(DatabaseActivity.this,
//                                RecyclerViewActivity.class));
//            }
//        }, 1000);

        FloatingActionButton fab = findViewById(R.id.add_new_product);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialogView();
            }
        });

        db = new MySqliteDatabase(this);

        showDataList();

//        showDataList();

//      INSERTING

//        Log.d("Insert: ", "Inserting...");
//        db.addProducts(new Shop("apple", 2, 15));
//        db.addProducts(new Shop("orange", 3, 12));
//        db.addProducts(new Shop("strawberry", 5, 10));

//      READING

        Log.d("Read: ", "Reading all products...");
        List<Shop> shopList = db.getAllShopProducts();

//      GETTING TABLE

        for (Shop shop : shopList) {
            String log = "ID: " + shop.getId() + " , Name: " +
                    shop.getNazwa() + " , Price: " + shop.getCena() +
                    " , Quantity: " + shop.getIlosc();

            Log.d("List: ", log);
        }
    }
//      GETTING PRODUCT

//        Shop product = db.getProduct(1);
//        product.setNazwa("pear");
//        product.setCena(4);
//        product.setIlosc(13);
//
//        Log.d("Get product: ", "Name: " + product.getNazwa() + " , Price: " + product.getCena() +
//                " , Quantity: " + product.getIlosc());

//      UPDATING

//        int newProduct = db.updateProduct(shop);
//
//        Log.d("Update product: ", "Updated row: " + String.valueOf(newProduct) +
//                " Name: " + shop.getNazwa() + " , Price: " + shop.getCena() +
//                " , Quantity: " + shop.getIlosc());
//    }

    public void createDialogView() {
        dialogBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.add_product, null);
        nameEdit = view.findViewById(R.id.product_name_edit);
        priceEdit = view.findViewById(R.id.product_price_edit);
        quantityEdit = view.findViewById(R.id.product_quantity_edit);
        saveButton = view.findViewById(R.id.save_button);
        dismissButton = view.findViewById(R.id.dismiss_button);

        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();
        dialog.show();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Todo: Save to Db
//                Todo: go to next screen
                if (!nameEdit.getText().toString().isEmpty() &&
                        !priceEdit.getText().toString().isEmpty() &&
                        !quantityEdit.getText().toString().isEmpty()) {
                    saveProductToDb(view);
                }
            }
        });

        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        startActivity
                                (new Intent(DatabaseActivity.this,
                                        RecyclerViewActivity.class));
                    }
                }, 1000);
            }
        });

    }

    private void saveProductToDb(View view) {

        Shop shop = new Shop();

        String newProductName = nameEdit.getText().toString();
        Double newProductPrice = Double.valueOf(priceEdit.getText().toString());
        Double newProductQuantity = Double.valueOf(quantityEdit.getText().toString());

        shop.setNazwa(newProductName);
        shop.setCena(newProductPrice);
        shop.setIlosc(newProductQuantity);

        db.addProducts(shop);
//    db.getProduct(shop.getId());

        Snackbar.make(view, "Item saved", Snackbar.LENGTH_LONG).show();

//        Log.d("item added: ", String.valueOf(db.getProductsCount()));


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                startActivity(new Intent(DatabaseActivity.this, RecyclerViewActivity.class));
            }
        }, 1000);
    }

    public void showDataList() {

        if (!db.getAllShopProducts().isEmpty())  {
            startActivity(new Intent(DatabaseActivity.this, RecyclerViewActivity.class));
            finish();
        }
    }
}


