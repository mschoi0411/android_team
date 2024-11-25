package com.example.application_team;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stage2Activity extends AppCompatActivity {

    // 카드 이미지 리소스 배열 (짝 포함)
    private static final int[] CARD_IMAGES = {
            R.drawable.card_image1, R.drawable.card_image5, // 카드 1의 짝
            R.drawable.card_image2, R.drawable.card_image6, // 카드 2의 짝
            R.drawable.card_image3, R.drawable.card_image7, // 카드 3의 짝
            R.drawable.card_image4, R.drawable.card_image8  // 카드 4의 짝
    };

    // 선택된 두 카드 정보
    private ImageView firstCard = null;
    private ImageView secondCard = null;
    private int firstCardImage = 0;
    private int secondCardImage = 0;
    private boolean isFlipping = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage2);

        // 카드 초기화 및 랜덤 배치
        setupCards();

        // 메인 화면으로 돌아가는 버튼
        findViewById(R.id.main_button_stage2).setOnClickListener(v -> {
            Intent intent = new Intent(Stage2Activity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    // 카드 셔플 및 설정
    private void setupCards() {
        // XML에서 정의된 카드 ID 배열
        int[] cardIds = {
                R.id.card_1, R.id.card_2, R.id.card_3, R.id.card_4,
                R.id.card_5, R.id.card_6, R.id.card_7, R.id.card_8
        };

        // 카드 이미지 섞기
        int[] shuffledCards = shuffleCards(CARD_IMAGES);

        // 각 카드에 클릭 리스너 설정
        for (int i = 0; i < cardIds.length; i++) {
            ImageView card = findViewById(cardIds[i]);
            final int cardImage = shuffledCards[i]; // 섞인 이미지

            // 카드 초기 상태 설정
            card.setImageResource(R.drawable.card_back); // 카드 뒷면
            card.setOnClickListener(v -> onCardClick(card, cardImage)); // 클릭 리스너 연결
        }
    }

    // 카드 이미지를 랜덤으로 섞기
    private int[] shuffleCards(int[] cards) {
        List<Integer> cardList = new ArrayList<>();
        for (int card : cards) {
            cardList.add(card);
        }
        Collections.shuffle(cardList); // 카드 섞기

        // 섞인 리스트를 배열로 변환
        int[] shuffledCards = new int[cardList.size()];
        for (int i = 0; i < cardList.size(); i++) {
            shuffledCards[i] = cardList.get(i);
        }
        return shuffledCards;
    }

    // 카드 클릭 이벤트
    private void onCardClick(ImageView card, int frontImage) {
        if (isFlipping || card == firstCard || card == secondCard) return;

        card.setImageResource(frontImage); // 카드 앞면 표시

        if (firstCard == null) {
            firstCard = card;
            firstCardImage = frontImage; // 첫 번째 카드 이미지 저장
        } else if (secondCard == null) {
            secondCard = card;
            secondCardImage = frontImage; // 두 번째 카드 이미지 저장
            checkMatch(); // 짝 확인
        }
    }

    // 카드 짝 확인
    private void checkMatch() {
        isFlipping = true;

        new Handler().postDelayed(() -> {
            if (isMatchingPair(firstCardImage, secondCardImage)) {
                Toast.makeText(this, "짝이 맞았습니다!", Toast.LENGTH_SHORT).show();
                firstCard.setEnabled(false); // 짝이 맞은 카드는 클릭 비활성화
                secondCard.setEnabled(false);
            } else {
                Toast.makeText(this, "짝이 아닙니다.", Toast.LENGTH_SHORT).show();
                firstCard.setImageResource(R.drawable.card_back); // 다시 뒷면으로
                secondCard.setImageResource(R.drawable.card_back);
            }

            // 상태 초기화
            firstCard = null;
            secondCard = null;
            firstCardImage = 0;
            secondCardImage = 0;
            isFlipping = false;
        }, 1000); // 1초 대기
    }

    // 짝 확인 로직
    private boolean isMatchingPair(int image1, int image2) {
        // 짝 규칙에 따라 이미지 비교
        return (image1 == R.drawable.card_image1 && image2 == R.drawable.card_image5) ||
                (image1 == R.drawable.card_image5 && image2 == R.drawable.card_image1) ||
                (image1 == R.drawable.card_image2 && image2 == R.drawable.card_image6) ||
                (image1 == R.drawable.card_image6 && image2 == R.drawable.card_image2) ||
                (image1 == R.drawable.card_image3 && image2 == R.drawable.card_image7) ||
                (image1 == R.drawable.card_image7 && image2 == R.drawable.card_image3) ||
                (image1 == R.drawable.card_image4 && image2 == R.drawable.card_image8) ||
                (image1 == R.drawable.card_image8 && image2 == R.drawable.card_image4);
    }
}
