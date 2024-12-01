package com.example.projectsample;

// 필수 안드로이드 라이브러리 임포트
import android.content.Intent; // 화면 전환을 위한 Intent 클래스
import android.os.Bundle; // Activity 상태 저장 및 복원
import android.util.Log; // 로그 출력을 위한 Log 클래스
import android.view.View; // UI 요소 처리를 위한 View 클래스
import android.widget.Button; // 버튼 위젯 클래스
import androidx.appcompat.app.AppCompatActivity; // 기본 Activity 클래스

// QuizMain 클래스 정의
public class QuizMain extends AppCompatActivity {

    private boolean isStage1Complete = false; // 1단계 완료 여부를 나타내는 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizmain); // quizmain 레이아웃 설정

        // ox문제 시작 버튼 설정
        Button startStage1 = findViewById(R.id.quiz_start_stage1);
        startStage1.setOnClickListener(v -> {
            // activity_oxquiz1로 이동
            Intent intent = new Intent(QuizMain.this, activity_oxquiz1.class);
            startActivity(intent); // 화면 전환
        });

        // 빈칸채우기 시작 버튼 설정
        Button startStage2 = findViewById(R.id.quiz_start_stage2);
        startStage2.setOnClickListener(v -> {
            // activity_shortanswer로 이동
            Intent intent = new Intent(QuizMain.this, activity_shortanswer.class);
            startActivity(intent); // 화면 전환
        });

        // 정답고르기 시작 버튼 설정
        Button startStage3 = findViewById(R.id.quiz_start_stage3);
        startStage3.setOnClickListener(v -> {
            // multiplechoice로 이동
            Intent intent = new Intent(QuizMain.this, multiplechoice.class);
            startActivity(intent); // 화면 전환
        });

        // 카드뒤집기 버튼 설정
        Button startStage4 = findViewById(R.id.quiz_start_stage4);
        startStage4.setOnClickListener(v -> {
            // Stage1Activity로 이동
            Intent intent = new Intent(QuizMain.this, Stage1Activity.class);
            startActivity(intent); // 화면 전환
        });

        // 메인으로 돌아가기 버튼 설정
        Button startStage5 = findViewById(R.id.quiz_start_stage5);
        startStage5.setOnClickListener(v -> {
            // MainActivity로 이동
            Intent intent = new Intent(QuizMain.this, MainActivity.class);
            startActivity(intent); // 화면 전환
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 1단계 성공 여부 확인
        isStage1Complete = checkStage1Completion(); // 단계 완료 여부 업데이트
    }

    // 1단계 완료 여부 확인 메서드
    private boolean checkStage1Completion() {
        // Stage1Activity의 성공 여부를 저장하는 SharedPreferences 활용 가능
        return false; // 기본값: 미완료
    }
}
