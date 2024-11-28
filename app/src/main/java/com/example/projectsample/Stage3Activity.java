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

public class Stage3Activity extends AppCompatActivity {

    private static final int[] CARD_IMAGES = {
            R.drawable.seven, R.drawable.josan1,
            R.drawable.korea4, R.drawable.josan2,
            R.drawable.josan5, R.drawable.josan3,
            R.drawable.card_image1, R.drawable.josan4
    };

    private ImageView firstCard = null;
    private ImageView secondCard = null;
    private int firstCardImage = 0;
    private int secondCardImage = 0;
    private boolean isFlipping = false;
    private int matchedPairs = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage3);

        setupCards();

        // 메인 화면으로 이동하는 버튼 설정
        Button mainMenuButton = findViewById(R.id.main_button_stage3);
        mainMenuButton.setOnClickListener(v -> {
            Intent intent = new Intent(Stage3Activity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Stage3Activity 종료
        });
    }

    private void setupCards() {
        int[] cardIds = {
                R.id.card_1, R.id.card_2, R.id.card_3, R.id.card_4,
                R.id.card_5, R.id.card_6, R.id.card_7, R.id.card_8
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
            if (firstCardImage == R.drawable.seven && secondCardImage == R.drawable.josan1 ||
                    firstCardImage == R.drawable.josan1 && secondCardImage == R.drawable.seven ||
                    firstCardImage == R.drawable.josan5 && secondCardImage == R.drawable.josan2 ||
                    firstCardImage == R.drawable.josan2 && secondCardImage == R.drawable.josan5 ||
                    firstCardImage == R.drawable.card_image1 && secondCardImage == R.drawable.josan3 ||
                    firstCardImage == R.drawable.josan3 && secondCardImage == R.drawable.card_image1 ||
                    firstCardImage == R.drawable.korea4 && secondCardImage == R.drawable.josan4 ||
                    firstCardImage == R.drawable.josan4 && secondCardImage == R.drawable.korea4) {

                Toast.makeText(this, "짝이 맞았습니다!", Toast.LENGTH_SHORT).show();
                firstCard.setEnabled(false);
                secondCard.setEnabled(false);
                matchedPairs++;

                if (matchedPairs == CARD_IMAGES.length / 2) {
                    Toast.makeText(this, "3단계를 성공했습니다!", Toast.LENGTH_SHORT).show();
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
