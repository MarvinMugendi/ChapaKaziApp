package com.marvedie.servicesmarketapp;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;

    private SliderAdapter sliderAdapter;

    private TextView[] mDots;

    private Button mNextBtn;
    private Button mBackBtn;
    private Button mFinishBtn;

    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSlideViewPager = findViewById(R.id.slideViewPager);
        mDotLayout = findViewById(R.id.dotsLayout);

        mNextBtn = findViewById(R.id.nextBtn);
        mBackBtn = findViewById(R.id.prevBtn);
        mFinishBtn = findViewById(R.id.finishBtn);

        sliderAdapter = new SliderAdapter(this);

        mSlideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);

        //OnclickListeners

        mFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });
        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideViewPager.setCurrentItem(mCurrentPage+1);
            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mSlideViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });
        }
        //dots creation
        public void addDotsIndicator(int position){

        mDots = new  TextView[3];
        mDotLayout.removeAllViews();

        for (int i = 0; i < mDots.length; i++){
          mDots[i] = new TextView( this);
          mDots[i].setText(Html.fromHtml("&#8226;"));
          mDots[i].setTextSize(35);
          mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

          mDotLayout.addView(mDots[i]);

        }
        if (mDots.length > 0){

            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }


        //Indicate position of slide
        ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                addDotsIndicator(i);
                mCurrentPage = i;

                if (i == 0){//first page
                    mNextBtn.setEnabled(true);
                    mBackBtn.setEnabled(false);
                    mBackBtn.setVisibility(View.INVISIBLE);
                    mFinishBtn.setEnabled(false);
                    mFinishBtn.setVisibility(View.INVISIBLE);

                    mNextBtn.setText("Next");
                    mBackBtn.setText("");
                    mFinishBtn.setText("");
                } else if ( i == mDots.length -1){

                    mNextBtn.setEnabled(false);
                    mBackBtn.setEnabled(true);
                    mBackBtn.setVisibility(View.VISIBLE);
                    mFinishBtn.setEnabled(true);
                    mFinishBtn.setVisibility(View.VISIBLE);


                    mNextBtn.setText("");
                    mBackBtn.setText("Back");
                    mFinishBtn.setText("Finish");
                } else {
                    mNextBtn.setEnabled(true);
                    mBackBtn.setEnabled(true);
                    mBackBtn.setVisibility(View.VISIBLE);
                    mFinishBtn.setEnabled(false);
                    mFinishBtn.setVisibility(View.INVISIBLE);

                    mNextBtn.setText("Next");
                    mBackBtn.setText("Back");
                    mFinishBtn.setText("");
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        };




}
