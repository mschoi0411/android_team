package com.example.projectsample;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class activity_shortanswer extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shortanswer);

        EditText answerInput = findViewById(R.id.answer_input);

        // 제출 버튼 설정
        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(v -> {
            String answer = answerInput.getText().toString().trim();

            // 정답 확인
            if (answer.equals("45")) {
                Toast.makeText(activity_shortanswer.this, "정답입니다!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity_shortanswer.this, "오답입니다. 다시 시도하세요!", Toast.LENGTH_SHORT).show();
            }
        });

        // 메인 화면으로 이동하는 버튼 설정
        Button mainMenuButton = findViewById(R.id.button);
        mainMenuButton.setOnClickListener(v -> {
            Intent intent = new Intent(activity_shortanswer.this, QuizMain.class);
            startActivity(intent);
            finish(); // Stage1Activity 종료
        });
    }
}
