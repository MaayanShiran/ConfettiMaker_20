package com.classy.confettimaker;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.classy.confetti.CircleParticle;
import com.classy.confetti.ConfettiView;
import com.classy.confetti.Particle;
import com.classy.confetti.RectangleParticle;
import com.classy.confetti.SnowflakeParticle;

public class MainDemo extends AppCompatActivity {

    private ConfettiView confettiView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        confettiView = findViewById(R.id.confettiView);

        findViewById(R.id.startButton).setOnClickListener(v -> {
            int shapeType = confettiView.getShapeType();
            Class<? extends Particle> particleClass;
            switch (shapeType) {
                case ConfettiView.SHAPE_RECTANGLE:
                    particleClass = RectangleParticle.class;
                    break;
                case ConfettiView.SHAPE_SNOWFLAKE:
                    particleClass = SnowflakeParticle.class;
                    break;
                case ConfettiView.SHAPE_CIRCLE:
                default:
                    particleClass = CircleParticle.class;
                    break;
            }
            confettiView.startConfetti(100, particleClass);
        });
    }
}

