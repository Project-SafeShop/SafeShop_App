package com.example.safeshopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Animation animation;
    ImageView logoImage,logoName,logoTagline;
    private static int SPLASH_SCREEN_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        animation = AnimationUtils.loadAnimation(this,R.anim.intro_animation);

        logoImage = findViewById(R.id.logoImage);
        logoName = findViewById(R.id.logoName);
        logoTagline = findViewById(R.id.logoTagline);

        logoImage.setAnimation(animation);
        logoName.setAnimation(animation);
        logoTagline.setAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run () {
                Intent intent = new Intent(MainActivity.this,HomePage.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN_TIME);

    }
}
