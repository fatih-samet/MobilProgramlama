package com.example.projerockpaperscissors;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_menu);

        Button btnSinglePlayer = findViewById(R.id.btnSinglePlayer);
        Button btnTwoPlayers = findViewById(R.id.btnTwoPlayers);

        btnSinglePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnTwoPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, TwoPlayers.class);
                startActivity(intent);
                finish();
            }
        });
    }
}