package com.example.projectsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class goryeo extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goryeo);
        // 버튼 참조
        Button button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(goryeo.this, MainActivity.class);
                intent.putExtra("pageId", "PAGE_4");
                startActivity(intent);
                finish();
            }
        });
    }
}
