package com.example.projectsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class gang_jum_gi extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gang_jum_gi);

        Button button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(gang_jum_gi.this, MainActivity.class);
                intent.putExtra("pageId", "PAGE_8");
                startActivity(intent);
                finish();
            }
        });
    }
}
