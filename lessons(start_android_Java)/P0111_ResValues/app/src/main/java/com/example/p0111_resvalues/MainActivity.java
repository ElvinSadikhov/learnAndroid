package com.example.p0111_resvalues;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView image;
    Button button;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.imageView);
        // changes a pic when we touch an image from pic_1 to pic_2 ONLY
        image.setOnClickListener(listener);
        // changes a pic when we touch a button(from one pic to another)
        button = findViewById(R.id.button);
        button.setOnClickListener(listener);

    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case (R.id.imageView):
                    image.setImageDrawable(getResources().getDrawable(R.drawable.pic_2));
                    break;
                case (R.id.button):
                    /* IT DOESN'T WORK
                    if (image.getDrawable().equals(R.drawable.pic_1))
                        image.setImageDrawable(getResources().getDrawable(R.drawable.pic_2));
                    else
                        image.setImageDrawable(getResources().getDrawable(R.drawable.pic_1));
                    break;*/
                    final Drawable drawable_1 = getResources().getDrawable(R.drawable.pic_1);
                    // will use these two BitMap object in order to compare
                    final Bitmap picMine = ((BitmapDrawable) image.getDrawable()).getBitmap();
                    final Bitmap pic_1 = ((BitmapDrawable) drawable_1).getBitmap();

                    if (picMine.equals(pic_1))
                        image.setImageDrawable(getResources().getDrawable(R.drawable.pic_2));
                    else
                        image.setImageDrawable(getResources().getDrawable(R.drawable.pic_1));
                    break;
            }
        }
    };
}