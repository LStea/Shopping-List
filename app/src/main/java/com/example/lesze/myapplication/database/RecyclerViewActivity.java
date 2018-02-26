package com.example.lesze.myapplication.database;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lesze.myapplication.R;
import com.example.lesze.myapplication.adapter.MyAdapter;
import com.example.lesze.myapplication.database.MySqliteDatabase;
import com.example.lesze.myapplication.model.Shop;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    MySqliteDatabase db;
    public List<Shop> itemsList;
    AlertDialog.Builder dialogBuilder;
    AlertDialog dialog;
    EditText nameEdit, quantityEdit, priceEdit;
    Button saveButton, dismissButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        FloatingActionButton fab = findViewById(R.id.add_product_to_database);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialogView();
            }
        });

        showListView();

    }

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
                dialog.dismiss();
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

        db.getAllShopProducts();
        Snackbar.make(view, "Item saved", Snackbar.LENGTH_LONG).show();

        dialog.dismiss();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                Intent intent = getIntent();
                finish();
                startActivity(intent);

            }
        }, 1000);
    }

        void showListView() {
            this.db = new MySqliteDatabase(this);

            RecyclerView recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            List<Shop> shoppingList;
            itemsList = new ArrayList<>();

            shoppingList = db.getAllShopProducts();

            for (Shop s : shoppingList) {
                Shop shop = new Shop();
                shop.setNazwa(s.getNazwa());
                shop.setCena(s.getCena());
                shop.setIlosc(s.getIlosc());
                shop.setId(s.getId());

                itemsList.add(shop);
            }

            MyAdapter recyclerViewAdapter = new MyAdapter(this, itemsList);
            recyclerView.setAdapter(recyclerViewAdapter);
            recyclerViewAdapter.notifyDataSetChanged();

        }
}
