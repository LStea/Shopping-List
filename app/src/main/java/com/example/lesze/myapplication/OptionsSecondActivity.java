package com.example.lesze.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OptionsSecondActivity extends AppCompatActivity {

    Button small, medium, large;
    Button red, blue, green;
    TextView instruction;
    Button showButton;

    private static final String PREF_NAME = "SharedPref";
    private static final String THIS_COLOR_KEY = "this_color_2";
    private static final String THIS_COLOR_NAME_KEY = "this_color_name_2";
    private static final String THIS_FONT_SIZE_KEY = "this_font_size_2";

    final String[] colors = {"red", "blue", "green"};
    final float[] sizes = {15, 25, 35};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_second);

//        TODO: find XML items

        instruction = findViewById(R.id.change_textview);

        red = findViewById(R.id.red_button);
        blue = findViewById(R.id.blue_button);
        green = findViewById(R.id.green_button);

        showButton = findViewById(R.id.save_button);

        small = findViewById(R.id.small_button);
        medium = findViewById(R.id.medium_button);
        large = findViewById(R.id.big_button);

//        TODO: Button listeners

        myColorClick(red, colors[0]);
        myColorClick(blue, colors[1]);
        myColorClick(green, colors[2]);

        mySizeClick(small, sizes[0]);
        mySizeClick(medium, sizes[1]);
        mySizeClick(large, sizes[2]);

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence xmlInstruction = getResources().getText(R.string.instruction);
                instruction.setText(xmlInstruction);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        int defaultValue = Color.parseColor("black");
        float size = sizes[1];
        instruction.setTextColor(sharedPref.getInt(THIS_COLOR_KEY, defaultValue));
        instruction.setText(sharedPref.getString(THIS_COLOR_NAME_KEY, "black"));
        instruction.setTextSize(sharedPref.getFloat(THIS_FONT_SIZE_KEY, size));
    }

    public void myColorClick(Button b, final String kolory) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instruction.setText(kolory);
                instruction.setTextColor(Color.parseColor(String.valueOf(kolory)));
                saveSharedPreferences();
            }
        });
    }

        public void mySizeClick(Button b, final float rozmiary) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //instruction.setTextSize(TypedValue.COMPLEX_UNIT_SP, rozmiary);
                    instruction.setTextSize(rozmiary);
                    saveSharedPreferences();
                }
            });
        }
            private void saveSharedPreferences() {
                SharedPreferences sharedPref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor defaultEditor = sharedPref.edit();
                defaultEditor.putInt(THIS_COLOR_KEY, instruction.getCurrentTextColor());
                defaultEditor.putString(THIS_COLOR_NAME_KEY, instruction.getText().toString());

                DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();
                float textsize = instruction.getTextSize() / metrics.density;

                defaultEditor.putFloat(THIS_FONT_SIZE_KEY, textsize);
                defaultEditor.commit();
            }
}
