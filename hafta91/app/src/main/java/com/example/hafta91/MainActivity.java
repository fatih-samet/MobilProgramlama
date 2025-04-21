package com.example.hafta91;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer, compass, gyroscope, humidity, light, magnetometer, pressure, proximity, thermometer;
    private Sensor currentSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SensorManager'ı başlat
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Sensörleri tanımla
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        compass = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION); // TYPE_ORIENTATION deprecated, alternatif kullanılmalı
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        humidity = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        pressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        thermometer = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        // Butonları tanımla ve onClick listener'larını ayarla
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);

        button1.setOnClickListener(v -> startSensor(accelerometer, "Accelerometer"));
        button2.setOnClickListener(v -> startSensor(compass, "Compass"));
        button3.setOnClickListener(v -> startSensor(gyroscope, "Gyroscope"));
        button4.setOnClickListener(v -> startSensor(humidity, "Humidity"));
        button5.setOnClickListener(v -> startSensor(light, "Light"));
        button6.setOnClickListener(v -> startSensor(magnetometer, "Magnetometer"));
        button7.setOnClickListener(v -> startSensor(pressure, "Pressure"));
        button8.setOnClickListener(v -> startSensor(proximity, "Proximity"));
        button9.setOnClickListener(v -> startSensor(thermometer, "Thermometer"));

        
    }

    private void startSensor(Sensor sensor, String sensorName) {
        // Önceki sensörü durdur
        if (currentSensor != null) {
            sensorManager.unregisterListener(this);
        }

        if (sensor != null) {
            currentSensor = sensor;
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            Toast.makeText(this, sensorName + " sensörü başlatıldı", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, sensorName + " sensörü bu cihazda mevcut değil", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Sensör verileri değiştiğinde buraya gelir
        String values = "";
        for (float value : event.values) {
            values += String.format("%.2f", value) + " ";
        }

        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                // Accelerometer verileri
                break;
            case Sensor.TYPE_ORIENTATION:
                // Compass verileri
                break;
            case Sensor.TYPE_GYROSCOPE:
                // Gyroscope verileri
                break;
            // Diğer sensörler için case'ler eklenebilir
        }

        // Sensör verilerini göster (örneğin bir TextView'da)
        // textView.setText(values);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Sensör hassasiyeti değiştiğinde
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Uygulama arka plana alındığında sensör dinleyicisini kaldır
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Uygulama ön plana geldiğinde son kullanılan sensörü yeniden başlat
        if (currentSensor != null) {
            sensorManager.registerListener(this, currentSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
}