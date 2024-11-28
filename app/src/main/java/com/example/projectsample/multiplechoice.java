package com.example.projectsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class multiplechoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplechoice);

        // 정답
        Button button1 = findViewById(R.id.option1Button3);
        // 오답
        Button button2 = findViewById(R.id.option2Button3);
        Button button3 = findViewById(R.id.option3Button3);
        Button button4 = findViewById(R.id.option4Button3);

        // 정답
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(multiplechoice.this, "정답입니다!", Toast.LENGTH_SHORT).show();
            }
        });

        // 오답
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(multiplechoice.this, "오답입니다. 다시 시도하세요!", Toast.LENGTH_SHORT).show();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(multiplechoice.this, "오답입니다. 다시 시도하세요!", Toast.LENGTH_SHORT).show();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(multiplechoice.this, "오답입니다. 다시 시도하세요!", Toast.LENGTH_SHORT).show();
            }
        });

        // 메인 화면으로 이동하는 버튼 설정
        Button mainMenuButton = findViewById(R.id.button2);
        mainMenuButton.setOnClickListener(v -> {
            Intent intent = new Intent(multiplechoice.this, QuizMain.class);
            startActivity(intent);
            finish(); // Stage1Activity 종료
        });
    }
}