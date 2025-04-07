package com.example.hafta71;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {
    int oran1=0,oran2=0,oran3=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        SeekBar sb1=(SeekBar) findViewById(R.id.seekBar1);
        SeekBar sb2=(SeekBar) findViewById(R.id.seekBar2);
        SeekBar sb3=(SeekBar) findViewById(R.id.seekBar3);

        TextView txt1=(TextView) findViewById(R.id.textView1);
        TextView txt2=(TextView) findViewById(R.id.textView2);
        TextView txt3=(TextView) findViewById(R.id.textView3);

        ConstraintLayout arkarenk=(ConstraintLayout) findViewById(R.id.arkaplan);

        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                sb1.setMax(255);
                int oran=android.graphics.Color.rgb(oran3,oran2,i);
                arkarenk.setBackgroundColor(oran);
                txt1.setText(String.valueOf(i));
                oran1=i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });

        sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                sb2.setMax(255);
                int oran=android.graphics.Color.rgb(oran3,i,oran1);
                arkarenk.setBackgroundColor(oran);
                txt2.setText(String.valueOf(i));
                oran2=i;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sb3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                sb3.setMax(255);
                int oran=android.graphics.Color.rgb(i,oran2,oran1);
                arkarenk.setBackgroundColor(oran);
                txt3.setText(String.valueOf(i));
                oran3=i;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}