package com.example.projectsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class multiplechoice extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplechoice);

        // 정답
        Button button1 = findViewById(R.id.option1Card3);
        // 오답
        Button button2 = findViewById(R.id.option2Card3);
        Button button3 = findViewById(R.id.option3Card3);
        Button button4 = findViewById(R.id.option4Card3);

        // 정답
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(multiplechoice.this, "정답입니다!", Toast.LENGTH_SHORT).show();
            }
        });

        // 오답
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(multiplechoice.this, "오답입니다. 다시 시도하세요!", Toast.LENGTH_SHORT).show();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(multiplechoice.this, "오답입니다. 다시 시도하세요!", Toast.LENGTH_SHORT).show();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(multiplechoice.this, "오답입니다. 다시 시도하세요!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}