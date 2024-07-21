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
    implementation("com.github.MaayanShiran:ConfettiMakerLibrary:1.00.03")
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
   />

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

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.classy.confetti.ConfettiView;
import com.classy.confetti.Particle;


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

```

## Demo Videos


<div style="display: flex; flex-wrap: wrap; gap: 10px;">
  <div style="flex: 1; min-width: 300px;">
    <iframe width="100%" height="200" src="https://github.com/user-attachments/assets/c89209df-15b9-4c92-806e-d5e12e7d9afd" frameborder="0" allowfullscreen></iframe>
  </div>
  <div style="flex: 1; min-width: 300px;">
    <iframe width="100%" height="200" src="https://github.com/user-attachments/assets/d5f79280-f70c-492e-ae8c-725188c42001" frameborder="0" allowfullscreen></iframe>
  </div>
  <div style="flex: 1; min-width: 300px;">
    <iframe width="100%" height="200" src="https://github.com/user-attachments/assets/7c915d1c-a587-437f-92ba-b4b24b74de79" frameborder="0" allowfullscreen></iframe>
  </div>
</div>
