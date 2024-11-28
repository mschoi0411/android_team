package com.example.projectsample;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class activity_oxquiz1 extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxquiz1);


    Button buttonO = findViewById(R.id.buttonO); // 올바른 ID 사용
    Button buttonX = findViewById(R.id.buttonX); // 올바른 ID 사용


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
        // 메인 화면으로 이동하는 버튼 설정
        Button mainMenuButton = findViewById(R.id.button2);
        mainMenuButton.setOnClickListener(v -> {
            Intent intent = new Intent(activity_oxquiz1.this, QuizMain.class);
            startActivity(intent);
            finish(); // Stage1Activity 종료
        });
}

}