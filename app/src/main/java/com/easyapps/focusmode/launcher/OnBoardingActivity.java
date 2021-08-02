package com.easyapps.focusmode.launcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

public class OnBoardingActivity extends AppCompatActivity {

    private ViewPager2 detailPager;
    private PagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        detailPager = findViewById(R.id.pager);
        viewPagerAdapter = PagerAdapter()
        detailPager.setAdapter();
        setDetails();
    }

    private void setDetails() {
        detailPager.
    }


}