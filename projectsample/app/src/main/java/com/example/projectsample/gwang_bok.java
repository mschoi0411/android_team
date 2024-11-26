package com.example.projectsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class gwang_bok extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gwang_bok);

        Button button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(gwang_bok.this, MainActivity.class);
                intent.putExtra("pageId", "PAGE_9");
                startActivity(intent);
                finish();
            }
        });
    }
}
