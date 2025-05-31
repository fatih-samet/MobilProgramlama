package com.example.projerockpaperscissors;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.Random;

public class TwoPlayers extends AppCompatActivity {
    private Choises player1Choice = null;
    private Choises player2Choice = null;
    int player1Score = 0;
    int player2Score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_two_players);

        Button btnPlayer1 = findViewById(R.id.btnPlayer1);
        Button btnPlayer2 = findViewById(R.id.btnPlayer2);
        ImageView imagePlayer1 = findViewById(R.id.imagePlayer1);
        ImageView imagePlayer2 = findViewById(R.id.imagePlayer2);
        TextView scoreText2 = findViewById(R.id.scoreText2);

        // Başlangıçta Player 2 butonunu devre dışı bırak
        btnPlayer2.setEnabled(false);

        btnPlayer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Player 1 butonuna basıldığında butonu devre dışı bırak
                btnPlayer1.setEnabled(false);
                // Player 2 butonunu etkinleştir
                btnPlayer2.setEnabled(true);

                int random1 = new Random().nextInt(3);

                if (random1 == 0) {
                    /*imagePlayer1.setImageResource(R.drawable.rock);*/
                    Glide.with(TwoPlayers.this)
                            .asGif()
                            .load("file:///android_asset/rock.gif")
                            .into(imagePlayer1);
                    player1Choice = Choises.ROCK;
                } else if (random1 == 1) {
                    /*imagePlayer1.setImageResource(R.drawable.paper);*/
                    Glide.with(TwoPlayers.this)
                            .asGif()
                            .load("file:///android_asset/paper.gif")
                            .into(imagePlayer1);
                    player1Choice = Choises.PAPER;
                } else {
                    /*imagePlayer1.setImageResource(R.drawable.scissors);*/
                    Glide.with(TwoPlayers.this)
                            .asGif()
                            .load("file:///android_asset/scissors.gif")
                            .into(imagePlayer1);
                    player1Choice = Choises.SCISSORS;
                }
            }
        });

        btnPlayer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Player 2 butonuna basıldığında Player 2 butonunu devre dışı bırak
                btnPlayer2.setEnabled(false);
                // Player 1 butonunu yeniden etkinleştir
                btnPlayer1.setEnabled(true);

                int random2 = new Random().nextInt(3);

                if (random2 == 0) {
                    /*imagePlayer2.setImageResource(R.drawable.rock);*/
                    Glide.with(TwoPlayers.this)
                            .asGif()
                            .load("file:///android_asset/rock.gif")
                            .into(imagePlayer2);
                    player2Choice = Choises.ROCK;
                } else if (random2 == 1) {
                    /*imagePlayer2.setImageResource(R.drawable.paper);*/
                    Glide.with(TwoPlayers.this)
                            .asGif()
                            .load("file:///android_asset/paper.gif")
                            .into(imagePlayer2);
                    player2Choice = Choises.PAPER;
                } else {
                    /*imagePlayer2.setImageResource(R.drawable.scissors);*/
                    Glide.with(TwoPlayers.this)
                            .asGif()
                            .load("file:///android_asset/scissors.gif")
                            .into(imagePlayer2);
                    player2Choice = Choises.SCISSORS;
                }

                if (player1Choice != null && player2Choice != null) {
                    if (player1Choice == player2Choice) {
                        // Draw, skor yok
                    } else if (
                            (player1Choice == Choises.ROCK && player2Choice == Choises.SCISSORS) ||
                                    (player1Choice == Choises.PAPER && player2Choice == Choises.ROCK) ||
                                    (player1Choice == Choises.SCISSORS && player2Choice == Choises.PAPER)
                    ) {
                        player1Score++;
                    } else {
                        player2Score++;
                    }

                    // Skor güncelle
                    scoreText2.setText("P1: " + player1Score + " P2: " + player2Score);

                    // Eğer bir oyuncu 3 skora ulaşmışsa yönlendirme yap
                    if (player1Score == 3) {
                        // Player 1 kazandı, Player1Wins sayfasına yönlendir
                        Intent intent = new Intent(TwoPlayers.this, player1wins.class);
                        startActivity(intent);
                        finish(); // Mevcut aktiviteyi bitir
                    } else if (player2Score == 3) {
                        // Player 2 kazandı, Player2Wins sayfasına yönlendir
                        Intent intent = new Intent(TwoPlayers.this, player2wins.class);
                        startActivity(intent);
                        finish(); // Mevcut aktiviteyi bitir
                    }

                    // Seçimler sıfırlanır
                    player1Choice = null;
                    player2Choice = null;
                }
            }
        });
    }
}
