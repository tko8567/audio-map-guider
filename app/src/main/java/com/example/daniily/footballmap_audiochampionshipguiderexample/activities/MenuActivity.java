package com.example.daniily.footballmap_audiochampionshipguiderexample.activities;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.daniily.footballmap_audiochampionshipguiderexample.R;

/**
 * Created by daniily on 2/17/18.
 * This class shows the Google map and the path user is considered to go
 */

public class MenuActivity extends AppCompatActivity {

    private LinearLayout mRoutesOptionView;
    private LinearLayout mSettingsOptionView;
    private LinearLayout mRoute1OptionView;
    private boolean routesOption = false;

    private DisplayMetrics metrics;
    private AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mRoutesOptionView = findViewById(R.id.menu_option_route_choice);
        mSettingsOptionView = findViewById(R.id.menu_option_settings);
        mRoute1OptionView = findViewById(R.id.route_1);

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
    }

    public void onMenuClick(View v) {
        int defaultDuration = 400;
        if (!routesOption) {

            mRoutesOptionView
                    .animate()
                    .yBy(-metrics.heightPixels / 4)
                    .setInterpolator(interpolator)
                    .setDuration(defaultDuration);

            mSettingsOptionView
                    .animate()
                    .alpha(0)
                    .setInterpolator(interpolator)
                    .setDuration(defaultDuration)
                    .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {}

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mSettingsOptionView.setOnClickListener(null);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {}

                        @Override
                        public void onAnimationRepeat(Animator animation) {}
                    });

            mRoute1OptionView
                    .animate()
                    .alpha(1)
                    .setInterpolator(interpolator)
                    .setDuration(defaultDuration)
                    .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            mRoute1OptionView.setOnClickListener(onRoute1Click);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {}

                        @Override
                        public void onAnimationCancel(Animator animation) {}

                        @Override
                        public void onAnimationRepeat(Animator animation) {}
                    });

        } else {
            mRoutesOptionView
                    .animate()
                    .yBy(metrics.heightPixels / 4)
                    .setInterpolator(interpolator)
                    .setDuration(defaultDuration);

            mSettingsOptionView
                    .animate()
                    .alpha(1)
                    .setInterpolator(interpolator)
                    .setDuration(defaultDuration)
                    .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            mSettingsOptionView.setOnClickListener(onSettingsClick);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {}

                        @Override
                        public void onAnimationCancel(Animator animation) {}

                        @Override
                        public void onAnimationRepeat(Animator animation) {}
                    });

            mRoute1OptionView
                    .animate()
                    .alpha(0)
                    .setInterpolator(interpolator)
                    .setDuration(defaultDuration)
                    .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            mRoute1OptionView.setOnClickListener(null);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {}

                        @Override
                        public void onAnimationCancel(Animator animation) {}

                        @Override
                        public void onAnimationRepeat(Animator animation) {}
                    });
        }
        routesOption = !routesOption;
    }

    private View.OnClickListener onSettingsClick = v -> {
        // do/do not play the sound
        // choose language
    };

    private View.OnClickListener onRoute1Click = v -> {
        Intent mapIntent = new Intent(MenuActivity.this, MapsActivity.class);
        startActivity(mapIntent);
    };


    private void callRoutesOptions() {


    }
}
