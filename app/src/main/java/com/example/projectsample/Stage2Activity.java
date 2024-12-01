package com.example.projectsample;

// 필수 안드로이드 라이브러리 및 클래스 임포트
import android.content.Intent; // 화면 전환을 위한 Intent 클래스
import android.os.Bundle; // Activity 상태 저장 및 복원
import android.os.Handler; // 딜레이 처리를 위한 Handler
import android.widget.Button; // 버튼 위젯 클래스
import android.widget.ImageView; // 이미지 뷰 위젯 클래스
import android.widget.ProgressBar; // 진행 상태를 나타내는 ProgressBar
import android.widget.Toast; // 사용자 알림 메시지용 Toast
import androidx.appcompat.app.AppCompatActivity; // 기본 Activity 클래스
import java.util.ArrayList; // 리스트 사용을 위한 ArrayList
import java.util.Collections; // 리스트 섞기를 위한 Collections
import java.util.List; // 리스트 인터페이스

// Stage2Activity 클래스 정의
public class Stage2Activity extends AppCompatActivity {

    // 카드 이미지 배열 (짝 포함)
    private static final int[] CARD_IMAGES = {
            R.drawable.iron, R.drawable.goldencrown,
            R.drawable.pottery, R.drawable.stoneblade,
            R.drawable.korea2, R.drawable.korea3
    };

    // 카드 클릭 이벤트 처리 시 사용할 변수들
    private ImageView firstCard = null; // 첫 번째 클릭된 카드
    private ImageView secondCard = null; // 두 번째 클릭된 카드
    private int firstCardImage = 0; // 첫 번째 카드의 이미지 리소스 ID
    private int secondCardImage = 0; // 두 번째 카드의 이미지 리소스 ID
    private boolean isFlipping = false; // 카드가 뒤집히는 중인지 확인
    private int matchedPairs = 0; // 맞춘 카드 쌍의 수

    // ProgressBar와 관련된 변수들
    private ProgressBar timeProgressBar; // 진행 상황을 표시할 ProgressBar
    private Handler handler = new Handler(); // ProgressBar 업데이트를 위한 Handler
    private int progress = 0; // ProgressBar 진행률
    private static final int TIME_LIMIT = 100; // 제한 시간 (100ms 단위)
    private boolean isGameComplete = false; // 게임 완료 상태 확인

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage2); // activity_stage2 레이아웃 설정

        setupCards(); // 카드 초기화 및 배치

        // 메인 화면으로 돌아가는 버튼 설정
        Button mainMenuButton = findViewById(R.id.main_button_stage2);
        mainMenuButton.setOnClickListener(v -> {
            Intent intent = new Intent(Stage2Activity.this, QuizMain.class); // 메인 화면으로 이동
            startActivity(intent); // 화면 전환
            finish(); // 현재 액티비티 종료
        });

        // ProgressBar 초기화
        timeProgressBar = findViewById(R.id.timeProgressBar2);

        // ProgressBar 업데이트를 위한 Handler 실행
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (progress < TIME_LIMIT && !isGameComplete) { // 게임 완료되지 않은 경우
                    progress++; // 진행률 증가
                    timeProgressBar.setProgress(progress); // ProgressBar 업데이트
                    handler.postDelayed(this, 200); // 200ms마다 실행
                } else if (!isGameComplete) { // 제한 시간이 초과된 경우
                    timeOut();
                }
            }
        }, 100); // 100ms 후 첫 실행
    }

    // 시간 초과 처리
    private void timeOut() {
        if (isGameComplete) return; // 게임이 완료된 경우 처리 무시

        // 시간 초과 메시지 표시
        Toast.makeText(this, "아쉽네요~! 공부를 더 하고 와요~!", Toast.LENGTH_SHORT).show();

        // 2초 후 메인 화면으로 이동
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(Stage2Activity.this, QuizMain.class); // 메인 화면 Intent 생성
            startActivity(intent); // 화면 전환
            finish(); // 현재 액티비티 종료
        }, 2000); // 2초 대기 후 실행
    }

    // 카드 초기화 및 랜덤 배치
    private void setupCards() {
        // 카드 뷰의 ID 배열
        int[] cardIds = {
                R.id.card_1, R.id.card_2, R.id.card_3,
                R.id.card_4, R.id.card_5, R.id.card_6
        };

        // 카드 이미지를 섞은 배열 생성
        int[] shuffledCards = shuffleCards(CARD_IMAGES);

        // 각 카드 뷰 초기화
        for (int i = 0; i < cardIds.length; i++) {
            ImageView card = findViewById(cardIds[i]); // 카드 뷰 ID에 해당하는 뷰 찾기
            final int cardImage = shuffledCards[i]; // 섞인 이미지 중 하나 선택

            // 초기 상태: 카드 앞면을 먼저 보여줌
            card.setImageResource(cardImage); // 카드 앞면으로 설정

            // 3초 후 카드 뒷면으로 전환 및 클릭 리스너 설정
            new Handler().postDelayed(() -> {
                card.setImageResource(R.drawable.card_back); // 카드 뒷면으로 변경
                card.setOnClickListener(v -> onCardClick(card, cardImage)); // 클릭 리스너 추가
            }, 5000); // 5초 대기 후 실행
        }
    }

    // 카드 이미지를 섞는 메서드
    private int[] shuffleCards(int[] cards) {
        List<Integer> cardList = new ArrayList<>(); // 카드 리스트 생성
        for (int card : cards) {
            cardList.add(card); // 카드 리스트에 추가
        }
        Collections.shuffle(cardList); // 리스트 섞기

        int[] shuffledCards = new int[cardList.size()];
        for (int i = 0; i < cardList.size(); i++) {
            shuffledCards[i] = cardList.get(i); // 리스트를 배열로 변환
        }
        return shuffledCards; // 섞인 배열 반환
    }

    // 카드 클릭 시 처리
    private void onCardClick(ImageView card, int frontImage) {
        if (isFlipping || card == firstCard || card == secondCard) return; // 클릭 무시 조건

        card.setImageResource(frontImage); // 클릭된 카드 앞면 표시

        if (firstCard == null) {
            firstCard = card; // 첫 번째 카드로 설정
            firstCardImage = frontImage;
        } else if (secondCard == null) {
            secondCard = card; // 두 번째 카드로 설정
            secondCardImage = frontImage;
            checkMatch(); // 두 카드가 선택되면 매칭 확인
        }
    }

    // 두 카드의 매칭 여부 확인
    private void checkMatch() {
        isFlipping = true; // 매칭 확인 중

        new Handler().postDelayed(() -> {
            // 매칭 성공 조건
            if (firstCardImage == R.drawable.iron && secondCardImage == R.drawable.goldencrown ||
                    firstCardImage == R.drawable.goldencrown && secondCardImage == R.drawable.iron ||
                    firstCardImage == R.drawable.pottery && secondCardImage == R.drawable.stoneblade ||
                    firstCardImage == R.drawable.stoneblade && secondCardImage == R.drawable.pottery ||
                    firstCardImage == R.drawable.korea2 && secondCardImage == R.drawable.korea3 ||
                    firstCardImage == R.drawable.korea3 && secondCardImage == R.drawable.korea2) {

                Toast.makeText(this, "짝이 맞았습니다!", Toast.LENGTH_SHORT).show();
                firstCard.setEnabled(false); // 첫 번째 카드 비활성화
                secondCard.setEnabled(false); // 두 번째 카드 비활성화
                matchedPairs++; // 맞춘 쌍 개수 증가

                // 모든 쌍을 맞춘 경우
                if (matchedPairs == CARD_IMAGES.length / 2) {
                    isGameComplete = true; // 게임 완료 상태 설정
                    Toast.makeText(this, "2단계를 성공했습니다!", Toast.LENGTH_SHORT).show();

                    // Stage3Activity로 이동
                    new Handler().postDelayed(() -> {
                        Intent intent = new Intent(Stage2Activity.this, Stage3Activity.class);
                        startActivity(intent); // Stage3Activity로 화면 전환
                        finish(); // 현재 액티비티 종료
                    }, 1500); // 1.5초 대기 후 실행
                }
            } else {
                // 매칭 실패 처리
                Toast.makeText(this, "짝이 아닙니다.", Toast.LENGTH_SHORT).show();
                firstCard.setImageResource(R.drawable.card_back); // 첫 번째 카드 뒷면으로 변경
                secondCard.setImageResource(R.drawable.card_back); // 두 번째 카드 뒷면으로 변경
            }

            // 상태 초기화
            firstCard = null;
            secondCard = null;
            firstCardImage = 0;
            secondCardImage = 0;
            isFlipping = false; // 매칭 확인 종료
        }, 1000); // 1초 대기 후 실행
    }
}
