package com.classy.confetti;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConfettiView extends View {

    public static final int SHAPE_CIRCLE = 0;
    public static final int SHAPE_RECTANGLE = 1;

    private List<ConfettiParticle> confettiParticles;
    private Paint paint;
    private Random random;
    private boolean isAnimating;
    private Handler handler;
    private Runnable updateRunnable;

    // Default characteristics
    private int confettiColor = Color.RED;
    private boolean useSolidColor = true;
    private float minSize = 5;
    private float maxSize = 10;
    private float minSpeedX = -3;
    private float maxSpeedX = 3;
    private float minSpeedY = 3;
    private float maxSpeedY = 6;
    private int shapeType = SHAPE_CIRCLE;

    public ConfettiView(Context context) {
        super(context);
        init();
    }

    public ConfettiView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        confettiParticles = new ArrayList<>();
        paint = new Paint();
        random = new Random();
        isAnimating = false;
        handler = new Handler();
        updateRunnable = new Runnable() {
            @Override
            public void run() {
                updateParticles();
                invalidate();
                handler.postDelayed(this, 16);
            }
        };
    }

    public void startConfetti(int count) {
        confettiParticles.clear();
        for (int i = 0; i < count; i++) {
            int color = useSolidColor ? confettiColor : Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            confettiParticles.add(new ConfettiParticle(
                    getWidth(), getHeight(), random,
                    color, minSize, maxSize,
                    minSpeedX, maxSpeedX, minSpeedY, maxSpeedY, shapeType));
        }
        isAnimating = true;
        handler.post(updateRunnable);
    }

    public void stopConfetti() {
        isAnimating = false;
        handler.removeCallbacks(updateRunnable);
    }

    private void updateParticles() {
        if (isAnimating) {
            for (ConfettiParticle particle : confettiParticles) {
                particle.update(getWidth(), getHeight());
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (ConfettiParticle particle : confettiParticles) {
            paint.setColor(particle.color);
            if (particle.shape == SHAPE_CIRCLE) {
                canvas.drawCircle(particle.x, particle.y, particle.size, paint);
            } else if (particle.shape == SHAPE_RECTANGLE) {
                canvas.save();
                canvas.rotate(particle.rotation, particle.x, particle.y);
                canvas.drawRect(particle.x, particle.y, particle.x + particle.width, particle.y + particle.height, paint);
                canvas.restore();
            }
        }
    }

    public void setConfettiColor(int color) {
        this.confettiColor = color;
    }

    public void setUseSolidColor(boolean useSolidColor) {
        this.useSolidColor = useSolidColor;
    }

    public void setSizeRange(float minSize, float maxSize) {
        this.minSize = minSize;
        this.maxSize = maxSize;
    }

    public void setSpeedXRange(float minSpeedX, float maxSpeedX) {
        this.minSpeedX = minSpeedX;
        this.maxSpeedX = maxSpeedX;
    }

    public void setSpeedYRange(float minSpeedY, float maxSpeedY) {
        this.minSpeedY = minSpeedY;
        this.maxSpeedY = maxSpeedY;
    }

    public void setShapeType(int shapeType) {
        this.shapeType = shapeType;
    }

    private static class ConfettiParticle {
        float x, y, size, speedX, speedY, width, height, rotation;
        int color, shape;
        Random random;

        ConfettiParticle(int width, int height, Random random, int color, float minSize, float maxSize, float minSpeedX, float maxSpeedX, float minSpeedY, float maxSpeedY, int shape) {
            this.random = random;
            this.shape = shape;
            x = random.nextFloat() * width;
            y = random.nextFloat() * height;
            size = random.nextFloat() * (maxSize - minSize) + minSize;
            speedX = random.nextFloat() * (maxSpeedX - minSpeedX) + minSpeedX;
            speedY = random.nextFloat() * (maxSpeedY - minSpeedY) + minSpeedY;
            this.color = color;

            if (shape == SHAPE_RECTANGLE) {
                this.width = size * (0.5f + random.nextFloat());
                this.height = size * (0.5f + random.nextFloat());
                this.rotation = random.nextFloat() * 360;
            }
        }

        void update(int width, int height) {
            x += speedX;
            y += speedY;
            if (y > height) {
                y = 0;
                x = random.nextFloat() * width;
            }
        }
    }
}

