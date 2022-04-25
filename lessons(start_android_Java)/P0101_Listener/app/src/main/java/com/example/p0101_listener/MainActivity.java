package com.example.p0101_listener;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textV;
    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textV = findViewById(R.id.textView);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);

        b1.setOnClickListener(listener);
        b2.setOnClickListener(listener);
        b3.setOnClickListener(listener);

        textV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b3.setText("you are named button4 now duddee");
            }
        });
    }

    // one Click Listener for all objects(buttons)
    // it is for sake of optimization
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button1:
                    // one of ways to do this (res/values/strings.xml)
                    textV.setText(R.string.text1);
                    break;
                case R.id.button2:
                    textV.setText("222привет");
                    break;
                case R.id.button3:
                    textV.setText("333привет");
                    break;
            }
        }

    };
}