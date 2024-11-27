package com.example.projectsample;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class silla extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.silla);

        // 이미지 삽입
        ImageView sillaimgview1 = findViewById(R.id.sillaimg1);
        sillaimgview1.setImageResource(R.drawable.silla1);

        ImageView sillaimgview2 = findViewById(R.id.sillaimg2);
        sillaimgview2.setImageResource(R.drawable.silla2);

        ImageView sillaimgview3 = findViewById(R.id.sillaimg3);
        sillaimgview3.setImageResource(R.drawable.silla3);

        ImageView sillaimgview4 = findViewById(R.id.sillaimg4);
        sillaimgview4.setImageResource(R.drawable.silla4);

        ImageView sillaimgview5 = findViewById(R.id.sillaimg5);
        sillaimgview5.setImageResource(R.drawable.silla5);

        ImageView sillaimgview6 = findViewById(R.id.sillaimg6);
        sillaimgview6.setImageResource(R.drawable.silla6);







        Button button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(silla.this, MainActivity.class);
                intent.putExtra("pageId", "PAGE_3");
                startActivity(intent);
                finish();
            }
        });


    }
}
