package com.classy.confetti;

import android.content.Context;
import android.content.res.TypedArray;
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
    public static final int SHAPE_SNOWFLAKE = 2;

    private List<Particle> particles;
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
    private float gravity = 0.5f; // Gravity effect
    private int shapeType = SHAPE_CIRCLE;

    public ConfettiView(Context context) {
        super(context);
        init(null);
    }

    public ConfettiView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ConfettiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ConfettiView);
            confettiColor = a.getColor(R.styleable.ConfettiView_confettiColor, confettiColor);
            useSolidColor = a.getBoolean(R.styleable.ConfettiView_useSolidColor, useSolidColor);
            minSize = a.getFloat(R.styleable.ConfettiView_minSize, minSize);
            maxSize = a.getFloat(R.styleable.ConfettiView_maxSize, maxSize);
            minSpeedX = a.getFloat(R.styleable.ConfettiView_minSpeedX, minSpeedX);
            maxSpeedX = a.getFloat(R.styleable.ConfettiView_maxSpeedX, maxSpeedX);
            minSpeedY = a.getFloat(R.styleable.ConfettiView_minSpeedY, minSpeedY);
            maxSpeedY = a.getFloat(R.styleable.ConfettiView_maxSpeedY, maxSpeedY);
            gravity = a.getFloat(R.styleable.ConfettiView_gravity, gravity);
            shapeType = a.getInt(R.styleable.ConfettiView_shapeType, shapeType);
            a.recycle();
        }

        particles = new ArrayList<>();
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

    public void startConfetti(int count, Class<? extends Particle> particleClass) {
        particles.clear();
        for (int i = 0; i < count; i++) {
            int color = useSolidColor ? confettiColor : Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            Particle particle = createParticle(particleClass, color);
            particles.add(particle);
        }
        isAnimating = true;
        handler.post(updateRunnable);
    }

    private Particle createParticle(Class<? extends Particle> particleClass, int color) {
        try {
            float startX = random.nextFloat() * getWidth();
            float startY = random.nextFloat() * getHeight();
            return particleClass.getConstructor(float.class, float.class, int.class, float.class, float.class, float.class, float.class, float.class, float.class, Random.class)
                    .newInstance(startX, startY, color, minSize, maxSize, minSpeedX, maxSpeedX, minSpeedY, maxSpeedY, random);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create particle", e);
        }
    }

    public void stopConfetti() {
        isAnimating = false;
        handler.removeCallbacks(updateRunnable);
    }

    private void updateParticles() {
        if (isAnimating) {
            for (Particle particle : particles) {
                particle.update(getWidth(), getHeight(), gravity);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Particle particle : particles) {
            particle.draw(canvas, paint);
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

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public void setShapeType(int shapeType) {
        this.shapeType = shapeType;
    }

    public int getShapeType() {
        return shapeType;
    }
}
