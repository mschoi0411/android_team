package com.example.application_team;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 메인 레이아웃 설정

        try {
            // Stage1Activity로 이동하는 버튼
            Button startStage1 = findViewById(R.id.start_stage1);
            if (startStage1 != null) {
                startStage1.setOnClickListener(v -> {
                    Intent intent = new Intent(MainActivity.this, Stage1Activity.class);
                    startActivity(intent);
                });
            } else {
                Log.e("MainActivity", "start_stage1 버튼이 null입니다.");
            }

            // Stage2Activity로 이동하는 버튼
            Button startStage2 = findViewById(R.id.start_stage2);
            if (startStage2 != null) {
                startStage2.setOnClickListener(v -> {
                    Intent intent = new Intent(MainActivity.this, Stage2Activity.class);
                    startActivity(intent);
                });
            } else {
                Log.e("MainActivity", "start_stage2 버튼이 null입니다.");
            }
//
//            // Stage3Activity로 이동하는 버튼
//            Button startStage3 = findViewById(R.id.start_stage3);
//            if (startStage3 != null) {
//                startStage3.setOnClickListener(v -> {
//                    Intent intent = new Intent(MainActivity.this, Stage3Activity.class);
//                    startActivity(intent);
//                });
//            } else {
//                Log.e("MainActivity", "start_stage3 버튼이 null입니다.");
//            }
        } catch (Exception e) {
            Log.e("MainActivity", "onCreate 오류 발생: " + e.getMessage());
        }
    }
}


