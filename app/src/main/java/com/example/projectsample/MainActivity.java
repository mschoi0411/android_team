package com.example.projectsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.BlurMaskFilter;


import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView progressText;

    private HashSet<String> visitedPages = new HashSet<>(); // 방문한 페이지를 기록
    private final int totalPages = 9; // 전체 페이지 수

    private static final String SHARED_PREFS_NAME = "VisitedPagesPrefs";
    private static final String VISITED_PAGES_KEY = "visitedPages";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TextView
        TextView appName = findViewById(R.id.appName);

        // GridLayout의 각 CardView
        CardView gojoseonCard = findViewById(R.id.gojoseon);
        CardView threeKingdomCard = findViewById(R.id.threekingdom);
        CardView shillaCard = findViewById(R.id.shilla);
        CardView goryeoCard = findViewById(R.id.goryeo);
        CardView balhaeCard = findViewById(R.id.balhae);
        CardView joseonCard = findViewById(R.id.joseon);
        CardView koreanEmpireCard = findViewById(R.id.koreanempire);
        CardView japanColonialCard = findViewById(R.id.japancolonial);
        CardView liberateCard = findViewById(R.id.liberate);

        // 하단 버튼
        Button quizButton = findViewById(R.id.quizButton);

        // SharedPreferences에서 방문한 페이지 불러오기
        loadVisitedPages();

        // 클릭 리스너 설정
        gojoseonCard.setOnClickListener(v -> navigateToPage(gojoseon.class, "PAGE_1"));
        threeKingdomCard.setOnClickListener(v -> navigateToPage(threecountry.class, "PAGE_2"));
        shillaCard.setOnClickListener(v -> navigateToPage(silla.class, "PAGE_3"));
        goryeoCard.setOnClickListener(v -> navigateToPage(goryeo.class, "PAGE_4"));
        balhaeCard.setOnClickListener(v -> navigateToPage(balhae.class, "PAGE_5"));
        joseonCard.setOnClickListener(v -> navigateToPage(joseon.class, "PAGE_6"));
        koreanEmpireCard.setOnClickListener(v -> navigateToPage(koreanempire.class, "PAGE_7"));
        japanColonialCard.setOnClickListener(v -> navigateToPage(gang_jum_gi.class, "PAGE_8"));
        liberateCard.setOnClickListener(v -> navigateToPage(gwang_bok.class, "PAGE_9"));
        quizButton.setOnClickListener(v -> navigateToPage(QuizMain.class, "Page_10"));

        // 진행률 나타내기
        progressBar = findViewById(R.id.progressBar);
        progressText = findViewById(R.id.progresstext);

        // Intent 데이터 처리
        Intent intent = getIntent();
        if (intent != null) {
            String pageId = intent.getStringExtra("pageId");
            // Log.d("Debug", "전달받은 pageId: " + pageId);
            if (pageId != null) {
                visitedPages.add(pageId); // 방문한 페이지 기록
                saveVisitedPages(); // 방문한 페이지 저장
                // Log.d("Debug", "현재 방문한 페이지: " + visitedPages.toString());
            }
        }

        // 진행률 업데이트
        updateProgress();
    }

    private void navigateToPage(Class<?> targetClass, String pageId) {
        Intent intent = new Intent(MainActivity.this, targetClass);
        intent.putExtra("pageId", pageId);
        startActivity(intent);
    }

    // 진행률 업데이트 메서드
    private void updateProgress() {
        int progress = (visitedPages.size() * 100) / totalPages; // 진행률 계산
        progressBar.setProgress(progress);
        progressText.setText("진행률: " + progress + "%");

        if (progress == 100) {
            showCustomToast();
        }
    }

    // 방문한 페이지 저장
    private void saveVisitedPages() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putStringSet(VISITED_PAGES_KEY, visitedPages); // HashSet 저장
        editor.apply();
        // Log.d("Debug", "방문한 페이지: " + visitedPages.toString());
    }

    // 방문 페이지 불러오기
    private void loadVisitedPages() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
        Set<String> savedPages = prefs.getStringSet(VISITED_PAGES_KEY, new HashSet<>());
        visitedPages.clear();
        visitedPages.addAll(savedPages);
        // Log.d("Debug", "불러온 방문한 페이지: " + visitedPages.toString());
    }

    private void showCustomToast() {

        // 커스텀 토스트 생성할 준비
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.main_custom_toast, findViewById(R.id.customToastLayout)); // 최상위 레이아웃 ID를 사용

        // 토스트 메시지 생성
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.setGravity(Gravity.CENTER, 0, 0);

        // 레이아웃을 블러 처리
        ConstraintLayout mainLayout = findViewById(R.id.mainLayout); // 메인 레이아웃을 가져옵니다.

        // 레이아웃에 블러 효과 적용
        mainLayout.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mainLayout.getBackground().setColorFilter(0x88000000, android.graphics.PorterDuff.Mode.DARKEN); // 어두운 색으로 덮어줍니다.
        mainLayout.getBackground().setAlpha(100); // 투명도 0~255로 조정하여 블러 효과 부여

        // 진행률 초기화
        visitedPages.clear();
        saveVisitedPages();
        updateProgress();

        // 토스트 메시지 표시
        toast.show();

        // 2초 후 블러 효과 제거
        mainLayout.postDelayed(() -> {
            mainLayout.getBackground().clearColorFilter();
            mainLayout.setLayerType(View.LAYER_TYPE_NONE, null);
        }, 2000);
    }



}
