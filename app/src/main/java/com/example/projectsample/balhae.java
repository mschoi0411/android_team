package com.example.projectsample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class balhae extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.balhae);
        // 버튼 참조
        Button button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(balhae.this, MainActivity.class);
                intent.putExtra("pageId", "PAGE_5");
                startActivity(intent);
                finish();
            }
        });
    }
}
