package com.classy.confetti;

import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.Random;

public abstract class Particle {
    float x, y, size, speedX, speedY, rotation, rotationSpeed;
    int color;
    Random random;

    public Particle(float startX, float startY, int color, float minSize, float maxSize, float minSpeedX, float maxSpeedX, float minSpeedY, float maxSpeedY, Random random) {
        this.random = random;
        this.x = startX;
        this.y = startY;
        this.size = random.nextFloat() * (maxSize - minSize) + minSize;
        this.speedX = random.nextFloat() * (maxSpeedX - minSpeedX) + minSpeedX;
        this.speedY = random.nextFloat() * (maxSpeedY - minSpeedY) + minSpeedY;
        this.rotation = random.nextFloat() * 360;
        this.rotationSpeed = random.nextFloat() * 10 - 5; // Random rotation speed
        this.color = color;
    }

    public void update(int width, int height, float gravity) {
        x += speedX;
        y += speedY;
        rotation += rotationSpeed;
        speedY += gravity;

        if (y > height) {
            y = 0;
            x = random.nextFloat() * width;
        }
    }

    public abstract void draw(Canvas canvas, Paint paint);
}
