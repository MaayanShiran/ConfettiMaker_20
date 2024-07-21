# ConfettiMakerLibrary 🎉

[![](https://jitpack.io/v/MaayanShiran/ConfettiMaker_20.svg)](https://jitpack.io/#MaayanShiran/ConfettiMaker_20)


ConfettiMakerLibrary is an Android library that provides a customizable ConfettiView for adding confetti animations to your applications.

## Features

- Customizable confetti shapes (circle, rectangle, snowflake)
- Configurable confetti colors, sizes, speeds, and gravity
- Easy integration with Android XML layout
- Supports dynamic runtime updates

## XML Attributes

- app:confettiColor - The color of the confetti (used if useSolidColor is true).
- app:useSolidColor - Whether to use a single color or random colors.
- app:minSize - The minimum size of the confetti.
- app:maxSize - The maximum size of the confetti.
- app:minSpeedX - The minimum horizontal speed of the confetti.
- app:maxSpeedX - The maximum horizontal speed of the confetti.
- app:minSpeedY - The minimum vertical speed of the confetti.
- app:maxSpeedY - The maximum vertical speed of the confetti.
- app:gravity - The gravity effect on the confetti.
- app:shapeType - The shape of the confetti (0 for circle, 1 for rectangle, 2 for snowflake).

## Java Methods

- setConfettiColor(int color) - Set the confetti color.
- setUseSolidColor(boolean useSolidColor) - Set whether to use a single color or random colors.
- setSizeRange(float minSize, float maxSize) - Set the size range of the confetti.
- setSpeedXRange(float minSpeedX, float maxSpeedX) - Set the horizontal speed range of the confetti.
- setSpeedYRange(float minSpeedY, float maxSpeedY) - Set the vertical speed range of the confetti.
- setGravity(float gravity) - Set the gravity effect on the confetti.
- setShapeType(int shapeType) - Set the shape type of the confetti.
- getShapeType() - Get the current shape type of the confetti.

## Installation

Add the JitPack repository to your root `build.gradle` file:

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Add the dependency to your module build.gradle file:
```
dependencies {
    implementation("com.github.MaayanShiran:ConfettiMakerLibrary:1.00.13")
}
```
## Usage Example

### XML Layout
```
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <com.classy.confetti.ConfettiView
        android:id="@+id/confettiView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:confettiColor="#FF0000"
        app:useSolidColor="true"
        app:minSize="5"
        app:maxSize="20"
        app:minSpeedX="-5"
        app:maxSpeedX="5"
        app:minSpeedY="3"
        app:maxSpeedY="10"
        app:gravity="0.5"
        app:shapeType="2" />

    <Button
        android:id="@+id/startButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Start Confetti"
        android:layout_centerInParent="true" />
</RelativeLayout>
```

### Java Code
```
package com.classy.confettimaker;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.classy.confetti.CircleParticle;
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
```

