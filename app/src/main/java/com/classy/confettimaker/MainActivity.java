package com.classy.confettimaker;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


import com.classy.confetti.ConfettiView;
import com.classy.confetti.Particle;
import com.classy.confetti.RectangleParticle;
import com.classy.confetti.SnowflakeParticle;

public class MainActivity extends AppCompatActivity {

    private ConfettiView confettiView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        confettiView = findViewById(R.id.confettiView);

        // Set confetti characteristics
        confettiView.setConfettiColor(Color.BLUE); // This is used only if useSolidColor is true
        confettiView.setUseSolidColor(true); // Set to true for solid color, false for different colors
        confettiView.setSizeRange(10, 20);
        confettiView.setSpeedXRange(-5, 5);
        confettiView.setSpeedYRange(5, 10);
        confettiView.setShapeType(ConfettiView.SHAPE_SNOW);

        findViewById(R.id.startButton).setOnClickListener(v -> confettiView.startConfetti(100, Particle.class));
    }

}
