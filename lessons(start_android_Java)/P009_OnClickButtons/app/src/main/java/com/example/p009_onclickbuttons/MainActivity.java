package com.example.p009_onclickbuttons;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MyLogs";
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

        // 1st way
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textV.setText("b1 effect");
                Toast myToast = Toast.makeText(MainActivity.this, "Button1 clicked successfully", Toast.LENGTH_SHORT);
                myToast.setGravity(Gravity.CENTER, 0, 0);

                // DOESN'T WORK ON API 30 + (R)
//                LinearLayout myToastView = (LinearLayout) myToast.getView();
//                ImageView imageView = new ImageView(MainActivity.this);
//                imageView.setImageResource(R.drawable.pic_1);
//                myToastView.addView(imageView,0);

                // FOR API 30 AND +
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_api30,
                        (ViewGroup) findViewById(R.id.toast_layout));

                myToast.show();

//                // FOR API 30 AND +
//                Toast toast = new Toast(getApplicationContext());
//                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//                toast.setDuration(Toast.LENGTH_LONG);
//                toast.setView(layout);
//                toast.show();

            }
        });

        // 2nd way
        b2.setOnClickListener(this);


    }

    // part of second way
    @Override
    public void onClick(View view) {
        textV.setText("b2 effect");
        try {
//            int temp = 6 / 3;
            int temp = 6 / 0;
            textV.setText("res of div: " + temp);
        } catch (Exception e) {
            Log.d(TAG, "division problem", e);
        }
        Log.d(TAG, "Button2 click finished");
    }

    // 3rd way
    // android:onClick="clickB3" in XML file(b3 configs)
    public void clickB3(View view) {
        textV.setText("b3 effect");
    }
}