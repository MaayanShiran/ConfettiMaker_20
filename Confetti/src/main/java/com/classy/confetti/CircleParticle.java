package com.classy.confetti;

import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.Random;

public class CircleParticle extends Particle {

    public CircleParticle(float startX, float startY, int color, float minSize, float maxSize, float minSpeedX, float maxSpeedX, float minSpeedY, float maxSpeedY, Random random) {
        super(startX, startY, color, minSize, maxSize, minSpeedX, maxSpeedX, minSpeedY, maxSpeedY, random);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        canvas.drawCircle(x, y, size, paint);
    }
}
