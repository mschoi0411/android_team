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

        // 대제목 설정
        TextView sillatitle = findViewById(R.id.sillatitle);
        sillatitle.setTextSize(30);
        sillatitle.setTypeface(null, Typeface.BOLD);

        // 소제목 설정
        TextView sillatitle1 = findViewById(R.id.sillatitle1);
        TextView sillatitle2 = findViewById(R.id.sillatitle2);
        TextView sillatitle3 = findViewById(R.id.sillatitle3);

        sillatitle1.setTextSize(22);
        sillatitle1.setTypeface(null, Typeface.BOLD);
        sillatitle2.setTextSize(22);
        sillatitle2.setTypeface(null, Typeface.BOLD);
        sillatitle3.setTextSize(22);
        sillatitle3.setTypeface(null, Typeface.BOLD);

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
