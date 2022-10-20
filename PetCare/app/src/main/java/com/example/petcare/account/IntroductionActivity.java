package com.example.petcare.account;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petcare.R;

public class IntroductionActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;
    private Animation topAnim, bottomAnim;
    private ImageView ivLogo;
    private TextView tvTitle, tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_introduction);

        bindingView();
        bindingAction();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(IntroductionActivity.this, LoginActivity.class);

                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(ivLogo, "logo");
                pairs[1] = new Pair<View, String>(tvTitle, "title");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(IntroductionActivity.this, pairs);
                startActivity(intent, options.toBundle());
                //startActivity(intent);

            }
        }, SPLASH_SCREEN);
    }

    private void bindingAction() {
        ivLogo.setAnimation(topAnim);
        tvTitle.setAnimation(bottomAnim);
        tvDescription.setAnimation(bottomAnim);

    }

    private void bindingView() {
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        ivLogo = findViewById(R.id.introductionLogo);
        tvTitle = findViewById(R.id.introductionTitle);
        tvDescription = findViewById(R.id.introductionDescription);
    }

}
