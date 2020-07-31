package com.ibm.cloud.appid.android.sample.appid;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.ibm.cloud.appid.android.api.AppID;
import com.ibm.cloud.appid.android.api.AppIDAuthorizationManager;
import com.ibm.cloud.appid.android.api.LoginWidget;

public class FlashScreen extends AppCompatActivity {

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
                Intent intent = new Intent(FlashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN_TIME);

    }
}
