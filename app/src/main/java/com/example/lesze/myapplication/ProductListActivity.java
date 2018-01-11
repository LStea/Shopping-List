package com.example.lesze.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lesze.myapplication.model.Shop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ProductListActivity extends AppCompatActivity {

    ListView listaZakupow;
    EditText productEditText;
    ArrayAdapter<String> myArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        listaZakupow = (ListView) findViewById(R.id.lista_zakupow);

        //productEditText = (EditText) findViewById(R.id.product_edit_text);

        final ArrayList<String> lista = new ArrayList<>();
        lista.addAll(Arrays.asList(Shop.zakupy));

        myArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);

//        if (listaZakupow != null) {
        listaZakupow.setAdapter(myArrayAdapter);
//        }

        listaZakupow.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Klik" + lista.get(position),
                        Toast.LENGTH_SHORT).show();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("List of products");

    }
}

//    public boolean myScanner() {
//        Scanner scanner = new Scanner(System.in);
//        produkt = scanner.nextLine();
//        return false;
//    }

//    public void dodajDoListy() {
//        if (myScanner()) {
//            myArrayAdapter.add(produkt);
//        }
//    }

//    void czyKupione() {
//        checkBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(ProductListActivity.this, "Klikniete", Toast.LENGTH_SHORT).show();
//            }
//
//        });
