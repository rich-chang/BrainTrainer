package com.richc.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        startButton.setVisibility(View.VISIBLE);
    }


    // goBtn click
    public void start(View view) {
        startButton.setVisibility(View.INVISIBLE);
    }
}
