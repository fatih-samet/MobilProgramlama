package com.example.bluetoothcamerawifi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bluetoothcamerawifi.Bluetooth;
import com.example.bluetoothcamerawifi.Camera;
import com.example.bluetoothcamerawifi.Wifi;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonB = (Button) findViewById(R.id.buttonB);
        Button buttonW = (Button) findViewById(R.id.buttonW);
        Button buttonC = (Button) findViewById(R.id.buttonC);

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecisB = new Intent(MainActivity.this, Bluetooth.class);
                startActivity(gecisB);
            }
        });

        buttonW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecisW = new Intent(MainActivity.this, Wifi.class);
                startActivity(gecisW);
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecisC = new Intent(MainActivity.this, Camera.class);
                startActivity(gecisC);
            }
   });
}
}