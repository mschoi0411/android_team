package com.example.projectsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView progressText;

    private HashSet<String> visitedPages = new HashSet<>(); // 방문한 페이지를 기록
    private final int totalPages = 9; // 전체 페이지 수

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

        // 신라 intent 넘기기
        shillaCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, silla.class);
            startActivity(intent);
        });
        // 대한제국 intent 넘기기
        koreanEmpireCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, koreanempire.class);
            startActivity(intent);
        });
        // 일제강점기 intent 넘기기
        japanColonialCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, gang_jum_gi.class);
            startActivity(intent);
        });
        // 광복 intent
        liberateCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, gwang_bok.class);
            startActivity(intent);
        });
        // 고조선 intent
        gojoseonCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, gojoseon.class);
            startActivity(intent);
        });
        // 삼국시대 intent
        threeKingdomCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, threecountry.class);
            startActivity(intent);
        });
        // 발해 intent
        balhaeCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, balhae.class);
            startActivity(intent);
        });

        // 진행률 나타내기
        progressBar = findViewById(R.id.progressBar);
        progressText = findViewById(R.id.progresstext);

        Intent intent = getIntent();
        if (intent != null) {
            String pageId = intent.getStringExtra("pageId");
            if (pageId != null) {
                visitedPages.add(pageId); // 방문한 페이지 기록
            }
        }

        updateProgress(); // 진행률 업데이트
    }

    // 진행률 업데이트 메서드
    private void updateProgress() {
        int progress = (visitedPages.size() * 100) / totalPages; // 진행률 계산
        progressBar.setProgress(progress);
        progressText.setText("진행률: " + progress + "%");
    }
}
