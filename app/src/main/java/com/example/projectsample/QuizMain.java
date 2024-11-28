package com.example.projectsample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class QuizMain extends AppCompatActivity {

    private boolean isStage1Complete = false; // 1단계 완료 여부

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizmain);

        Button startStage1 = findViewById(R.id.quiz_start_stage1);
        startStage1.setOnClickListener(v -> {
            Intent intent = new Intent(QuizMain.this, activity_oxquiz1.class);
            startActivity(intent);
        });

        Button startStage2 = findViewById(R.id.quiz_start_stage2);
        startStage2.setOnClickListener(v -> {
            Intent intent = new Intent(QuizMain.this, activity_shortanswer.class);
            startActivity(intent);
        });

        Button startStage3 = findViewById(R.id.quiz_start_stage3);
        startStage3.setOnClickListener(v -> {
            Intent intent = new Intent(QuizMain.this, multiplechoice.class);
            startActivity(intent);
        });

        Button startStage4 = findViewById(R.id.quiz_start_stage4);
        startStage4.setOnClickListener(v -> {
            Intent intent = new Intent(QuizMain.this, Stage1Activity.class);
            startActivity(intent);
        });


        }

    @Override
    protected void onResume() {
        super.onResume();
        // 1단계 성공 여부 확인
        isStage1Complete = checkStage1Completion();
    }

    private boolean checkStage1Completion() {
        // Stage1Activity 성공 여부를 저장하는 SharedPreferences 활용 가능
        return false; // 기본값: 미완료
    }
}
