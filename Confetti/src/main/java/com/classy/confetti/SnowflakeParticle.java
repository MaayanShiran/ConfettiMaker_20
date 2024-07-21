package com.classy.confetti;

import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.Random;

public class SnowflakeParticle extends Particle {

    public SnowflakeParticle(float startX, float startY, int color, float minSize, float maxSize, float minSpeedX, float maxSpeedX, float minSpeedY, float maxSpeedY, Random random) {
        super(startX, startY, color, minSize, maxSize, minSpeedX, maxSpeedX, minSpeedY, maxSpeedY, random);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        paint.setStrokeWidth(size / 5);
        canvas.save();
        canvas.rotate(rotation, x, y);
        for (int i = 0; i < 6; i++) {
            canvas.drawLine(x, y - size, x, y + size, paint);
            canvas.rotate(60, x, y);
        }
        canvas.restore();
    }
}
