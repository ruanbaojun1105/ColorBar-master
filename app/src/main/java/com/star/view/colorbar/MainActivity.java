package com.star.view.colorbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ColorBar colorBar;
    ColorValueBar colorValueBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        colorBar=findViewById(R.id.colorBar);
        colorValueBar=findViewById(R.id.colorValueBar);
//        colorBar.setThumbColor(int);
//        colorBar.setThumbBorderColor(int);
//        colorBar.setThumbBorderWidth(float);
//        colorBar.setValueBar(ColorValueBar)
//        colorBar.setColor(int);
//        colorBar.addOnColorChangedListener(listener);
//        colorValueBar.addOnColorChangedListener(listener);
    }
}
