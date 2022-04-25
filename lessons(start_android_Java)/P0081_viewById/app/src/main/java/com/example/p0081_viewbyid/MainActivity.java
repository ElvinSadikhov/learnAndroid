package com.example.p0081_viewbyid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView myTextView = (TextView) findViewById(R.id.mainTextView);
        myTextView.setText("Hi,dude");

        Button button1 = findViewById(R.id.button1);
        button1.setText("I've just changed this button");

        Button button2 = findViewById(R.id.button2);
        button2.setText("Second button of mine");

        CheckBox myCheckBox = findViewById(R.id.checkBox);
        myCheckBox.setText("Did it change?)");
        myCheckBox.setChecked(true);

    }
}