package com.classy.confetti;

import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.Random;

public class RectangleParticle extends Particle {

    public RectangleParticle(float startX, float startY, int color, float minSize, float maxSize, float minSpeedX, float maxSpeedX, float minSpeedY, float maxSpeedY, Random random) {
        super(startX, startY, color, minSize, maxSize, minSpeedX, maxSpeedX, minSpeedY, maxSpeedY, random);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        canvas.save();
        canvas.rotate(rotation, x, y);
        canvas.drawRect(x, y, x + size, y + size, paint);
        canvas.restore();
    }
}
