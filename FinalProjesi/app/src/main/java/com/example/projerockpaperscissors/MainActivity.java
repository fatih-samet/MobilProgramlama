package com.example.projerockpaperscissors;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.content.ContextCompat;
import java.util.Random;
import android.content.Context;

public class MainActivity extends AppCompatActivity {
    ImageView aiImage, yourImage;
    Button rockButton, paperButton, scissorsButton;
    TextView scoreText;

    int yourScore = 0;
    int aiScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // 🚀 🛠 findViewById çağrılarını burada yapıyoruz
        aiImage = findViewById(R.id.aiImage);
        yourImage = findViewById(R.id.yourImage);
        scoreText = findViewById(R.id.scoreText);
        rockButton = findViewById(R.id.rockButton);
        paperButton = findViewById(R.id.paperButton);
        scissorsButton = findViewById(R.id.scissorsButton);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 🚀 🛠 DOĞRU ONCLICK TANIMLAMALARI
        rockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yourImage.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.rock));
                Results result = calculateResult(Choises.ROCK, AIChoise(v.getContext()));
                updateScore(result);
            }
        });

        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yourImage.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.paper));
                Results result = calculateResult(Choises.PAPER, AIChoise(v.getContext()));
                updateScore(result);
            }
        });

        scissorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yourImage.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.scissors));
                Results result = calculateResult(Choises.SCISSORS, AIChoise(v.getContext()));
                updateScore(result);
            }
        });
    }

    public void updateScore(Results result) {
        if (result.equals(Results.WIN)) {
            yourScore++;
        } else if (result.equals(Results.LOST)) {
            aiScore++;
        }
        scoreText.setText("AI: " + aiScore + " YOUR: " + yourScore);  // 🚀 BOŞLUK DÜZELTİLDİ
    }

    public Results calculateResult(Choises yourChoises, Choises aiChoise) {
        if (yourChoises.equals(aiChoise)) {
            return Results.DRAW;
        } else if ((yourChoises.equals(Choises.ROCK) && aiChoise.equals(Choises.SCISSORS)) ||
                (yourChoises.equals(Choises.PAPER) && aiChoise.equals(Choises.ROCK)) ||
                (yourChoises.equals(Choises.SCISSORS) && aiChoise.equals(Choises.PAPER))) {
            return Results.WIN;
        } else {
            return Results.LOST;
        }
    }

    public Choises AIChoise(Context context) {
        int random = new Random().nextInt(3);

        if (random == 0) {
            aiImage.setBackground(ContextCompat.getDrawable(context, R.drawable.rock));
            return Choises.ROCK;
        } else if (random == 1) {
            aiImage.setBackground(ContextCompat.getDrawable(context, R.drawable.paper));
            return Choises.PAPER;
        } else {
            aiImage.setBackground(ContextCompat.getDrawable(context, R.drawable.scissors));
            return Choises.SCISSORS;
        }
    }
}
