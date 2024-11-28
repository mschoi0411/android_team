package com.example.projectsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class activity_oxquiz1 extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxquiz1);



        Button buttonO = findViewById(R.id.buttonOCard1);

        Button buttonX = findViewById(R.id.buttonXCard1);


        buttonO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 정답 처리
                Toast.makeText(activity_oxquiz1.this, "정답입니다!", Toast.LENGTH_SHORT).show();
            }
        });


        buttonX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 오답 처리
                Toast.makeText(activity_oxquiz1.this, "오답입니다. 다시 시도하세요!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
