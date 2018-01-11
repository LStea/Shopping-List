package com.example.lesze.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.lesze.myapplication.database.MySqliteDatabase;

public class MainActivity extends AppCompatActivity {

    Button listaZakupow;
    Button opcje;
    Button dane;
    MySqliteDatabase myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaZakupow = (Button) findViewById(R.id.lista_produktow);
        opcje = (Button) findViewById(R.id.opcje);

        dane = (Button) findViewById(R.id.database_helper);

        listaZakupow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listaZakupow = new Intent(MainActivity.this, ProductListActivity.class);
                startActivity(listaZakupow);
            }
        });

        opcje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent opcje = new Intent(MainActivity.this, OptionsSecondActivity.class);
                startActivity(opcje);
            }
        });

        dane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent dane = new Intent(MainActivity.this, DatabaseActivity.class);
                startActivity(dane);

            }
        });

    }
}

