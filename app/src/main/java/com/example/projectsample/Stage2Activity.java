package com.example.projectsample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.widget.ProgressBar;

public class Stage2Activity extends AppCompatActivity {

    private static final int[] CARD_IMAGES = {
            R.drawable.iron, R.drawable.goldencrown,
            R.drawable.pottery, R.drawable.stoneblade,
            R.drawable.korea2, R.drawable.korea3
    };

    private ImageView firstCard = null;
    private ImageView secondCard = null;
    private int firstCardImage = 0;
    private int secondCardImage = 0;
    private boolean isFlipping = false;
    private int matchedPairs = 0;
    private ProgressBar timeProgressBar;
    private Handler handler = new Handler();
    private int progress = 0;
    private static final int TIME_LIMIT = 100;
    private boolean isGameComplete = false; // 게임 완료 상태 확인

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage2);

        setupCards();

        // 메인 화면으로 이동하는 버튼 설정
        Button mainMenuButton = findViewById(R.id.main_button_stage2);
        mainMenuButton.setOnClickListener(v -> {
            Intent intent = new Intent(Stage2Activity.this, QuizMain.class);
            startActivity(intent);
            finish(); // Stage2Activity 종료
        });

        timeProgressBar = findViewById(R.id.timeProgressBar2);

        // ProgressBar 업데이트 (1초마다 실행)
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (progress < TIME_LIMIT && !isGameComplete) { // 게임 완료되지 않은 경우만 실행
                    progress++;
                    timeProgressBar.setProgress(progress);
                    handler.postDelayed(this, 200); // 100ms마다 실행
                } else if (!isGameComplete) { // 시간 초과 시 처리
                    timeOut();
                }
            }
        }, 100);
    }

    private void timeOut() {
        if (isGameComplete) return; // 게임이 완료된 경우 시간 초과 처리 무시

        // 시간 초과 메시지와 화면 이동
        Toast.makeText(this, "아쉽네요~! 공부를 더 하고 와요~!", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(Stage2Activity.this, QuizMain.class);
            startActivity(intent);
            finish(); // Stage1Activity 종료
        }, 2000); // 2초 대기 후 이동
    }


    private void setupCards() {
        int[] cardIds = {
                R.id.card_1, R.id.card_2, R.id.card_3,
                R.id.card_4, R.id.card_5, R.id.card_6
        };

        int[] shuffledCards = shuffleCards(CARD_IMAGES);

        for (int i = 0; i < cardIds.length; i++) {
            ImageView card = findViewById(cardIds[i]);
            final int cardImage = shuffledCards[i];

            // 초기 상태: 카드의 앞면을 먼저 보여줌
            card.setImageResource(cardImage); // 카드 앞면으로 초기화

            // 3초 후 카드 뒷면으로 전환 및 클릭 리스너 설정
            new Handler().postDelayed(() -> {
                card.setImageResource(R.drawable.card_back); // 카드 뒷면으로 전환
                card.setOnClickListener(v -> onCardClick(card, cardImage)); // 클릭 리스너 설정
            }, 5000); // 3초 대기
        }
    }

    private int[] shuffleCards(int[] cards) {
        List<Integer> cardList = new ArrayList<>();
        for (int card : cards) {
            cardList.add(card);
        }
        Collections.shuffle(cardList);

        int[] shuffledCards = new int[cardList.size()];
        for (int i = 0; i < cardList.size(); i++) {
            shuffledCards[i] = cardList.get(i);
        }
        return shuffledCards;
    }

    private void onCardClick(ImageView card, int frontImage) {
        if (isFlipping || card == firstCard || card == secondCard) return;

        card.setImageResource(frontImage);

        if (firstCard == null) {
            firstCard = card;
            firstCardImage = frontImage;
        } else if (secondCard == null) {
            secondCard = card;
            secondCardImage = frontImage;
            checkMatch();
        }
    }

    private void checkMatch() {
        isFlipping = true;

        new Handler().postDelayed(() -> {
            if (firstCardImage == R.drawable.iron && secondCardImage == R.drawable.goldencrown ||
                    firstCardImage == R.drawable.goldencrown && secondCardImage == R.drawable.iron ||
                    firstCardImage == R.drawable.pottery && secondCardImage == R.drawable.stoneblade ||
                    firstCardImage == R.drawable.stoneblade && secondCardImage == R.drawable.pottery ||
                    firstCardImage == R.drawable.korea2 && secondCardImage == R.drawable.korea3 ||
                    firstCardImage == R.drawable.korea3 && secondCardImage == R.drawable.korea2) {

                Toast.makeText(this, "짝이 맞았습니다!", Toast.LENGTH_SHORT).show();
                firstCard.setEnabled(false);
                secondCard.setEnabled(false);
                matchedPairs++;

                if (matchedPairs == CARD_IMAGES.length / 2) {
                    isGameComplete = true; // 게임 완료 상태 설정
                    Toast.makeText(this, "2단계를 성공했습니다!", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(() -> {
                        Intent intent = new Intent(Stage2Activity.this, Stage3Activity.class);
                        startActivity(intent);
                        finish();
                    }, 1500);
                }
            } else {
                Toast.makeText(this, "짝이 아닙니다.", Toast.LENGTH_SHORT).show();
                firstCard.setImageResource(R.drawable.card_back);
                secondCard.setImageResource(R.drawable.card_back);
            }

            firstCard = null;
            secondCard = null;
            firstCardImage = 0;
            secondCardImage = 0;
            isFlipping = false;
        }, 1000);
    }
}

