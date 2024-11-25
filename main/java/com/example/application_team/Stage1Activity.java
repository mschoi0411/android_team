package com.example.application_team;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Stage1Activity extends AppCompatActivity {

    // 각 카드의 리소스 (앞면 이미지)
    private static final int CARD_1_FRONT = R.drawable.card_image1;
    private static final int CARD_2_FRONT = R.drawable.card_image2;
    private static final int CARD_3_FRONT = R.drawable.card_image3;
    private static final int CARD_4_FRONT = R.drawable.card_image4;
    private static final int CARD_BACK = R.drawable.card_back; // 카드 뒷면 이미지

    // 선택된 두 카드
    private ImageView firstCard = null;
    private ImageView secondCard = null;

    // 카드 클릭 방지 상태
    private boolean isFlipping = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage1);

        // 각 카드 ImageView 참조
        ImageView card1 = findViewById(R.id.card_1);
        ImageView card2 = findViewById(R.id.card_2);
        ImageView card3 = findViewById(R.id.card_3);
        ImageView card4 = findViewById(R.id.card_4);

        // 카드 클릭 리스너 설정
        card1.setOnClickListener(v -> onCardClick(card1, CARD_1_FRONT));
        card2.setOnClickListener(v -> onCardClick(card2, CARD_2_FRONT));
        card3.setOnClickListener(v -> onCardClick(card3, CARD_3_FRONT));
        card4.setOnClickListener(v -> onCardClick(card4, CARD_4_FRONT));

        // 메인 화면으로 가기 버튼
        findViewById(R.id.main_button).setOnClickListener(v -> {
            Intent intent = new Intent(Stage1Activity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void onCardClick(ImageView card, int frontImage) {
        if (isFlipping || card == firstCard || card == secondCard) return;

        // 카드 뒤집기
        card.setImageResource(frontImage);

        if (firstCard == null) {
            firstCard = card; // 첫 번째 카드 선택
        } else if (secondCard == null) {
            secondCard = card; // 두 번째 카드 선택
            checkMatch(); // 짝 확인
        }
    }

    private void checkMatch() {
        isFlipping = true; // 클릭 방지 상태 설정

        // 딜레이 후 결과 확인
        new Handler().postDelayed(() -> {
            if (firstCard == null || secondCard == null) return;

            // 카드 짝 확인
            if (isMatchingPair(firstCard, secondCard)) {
                Toast.makeText(this, "짝이 맞았습니다!", Toast.LENGTH_SHORT).show();
                firstCard.setEnabled(false);
                secondCard.setEnabled(false);
            } else {
                // 짝이 맞지 않으면 다시 뒤집기
                Toast.makeText(this, "짝이 아닙니다.", Toast.LENGTH_SHORT).show();
                firstCard.setImageResource(CARD_BACK);
                secondCard.setImageResource(CARD_BACK);
            }

            // 상태 초기화
            firstCard = null;
            secondCard = null;
            isFlipping = false;
        }, 1000); // 1초 대기
    }

    private boolean isMatchingPair(ImageView card1, ImageView card2) {
        // 카드의 Drawable 상태 비교
        return (card1.getDrawable().getConstantState() == getResources().getDrawable(CARD_1_FRONT).getConstantState() &&
                card2.getDrawable().getConstantState() == getResources().getDrawable(CARD_3_FRONT).getConstantState()) ||
                (card1.getDrawable().getConstantState() == getResources().getDrawable(CARD_3_FRONT).getConstantState() &&
                        card2.getDrawable().getConstantState() == getResources().getDrawable(CARD_1_FRONT).getConstantState()) ||
                (card1.getDrawable().getConstantState() == getResources().getDrawable(CARD_2_FRONT).getConstantState() &&
                        card2.getDrawable().getConstantState() == getResources().getDrawable(CARD_4_FRONT).getConstantState()) ||
                (card1.getDrawable().getConstantState() == getResources().getDrawable(CARD_4_FRONT).getConstantState() &&
                        card2.getDrawable().getConstantState() == getResources().getDrawable(CARD_2_FRONT).getConstantState());
    }
}



