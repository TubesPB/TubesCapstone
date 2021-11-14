package com.capstone.tubescapstone;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.github.appintro.AppIntro;
import com.github.appintro.AppIntroFragment;
import com.github.appintro.AppIntroPageTransformerType;

public class IntroActivity extends AppIntro {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(AppIntroFragment.newInstance(
                "",
                "",
                R.drawable.main4,
                Color.WHITE,
                this.getResources().getColor(R.color.purple),
                this.getResources().getColor(R.color.purple_700)
        ));
        addSlide(AppIntroFragment.newInstance(
                "",
                "",
                R.drawable.main2,
                Color.WHITE,
                this.getResources().getColor(R.color.purple),
                this.getResources().getColor(R.color.purple_700)
        ));
        addSlide(AppIntroFragment.newInstance(
                "",
                " ",
                R.drawable.main3,
                Color.WHITE,
                this.getResources().getColor(R.color.orange),
                this.getResources().getColor(R.color.purple_700)
        ));
        setNextArrowColor(this.getResources().getColor(R.color.purple));
        setColorDoneText(this.getResources().getColor(R.color.purple));
        setColorSkipButton(this.getResources().getColor(R.color.purple));
        setIndicatorColor(this.getResources().getColor(R.color.purple), this.getResources().getColor(R.color.purple_500));
        setTransformer((AppIntroPageTransformerType) AppIntroPageTransformerType.Flow.INSTANCE);
    }

    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Decide what to do when the user clicks on "Skip"
        changePrefFirstRun();
        finish();
    }

    public void onDonePressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Decide what to do when the user clicks on "Skip"
        changePrefFirstRun();
        finish();
    }

    public void changePrefFirstRun() {
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }
}
