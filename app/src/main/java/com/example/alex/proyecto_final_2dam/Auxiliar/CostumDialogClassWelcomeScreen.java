package com.example.alex.proyecto_final_2dam.Auxiliar;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.alex.proyecto_final_2dam.R;

public class CostumDialogClassWelcomeScreen extends Dialog implements android.view.View.OnClickListener {
    private android.support.v4.view.ViewPager viewPager ;
    private LinearLayout mDotLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;
    private Context context1;

    public CostumDialogClassWelcomeScreen(@NonNull Context context) {
        super(context);
        context1=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_welcome_screen_fragmetn);
        viewPager=(ViewPager)findViewById(R.id.ViewPager);
        mDotLayout=(LinearLayout) findViewById(R.id.ads_Layout);
        sliderAdapter = new SliderAdapter(this.getContext());
        viewPager.setAdapter(sliderAdapter);


        viewPager.addOnPageChangeListener(viewListener);

    }

    @Override
    public void onClick(View v) {

    }


    public void addDotsIndicator(int pos){
        mDots = new TextView[3];
        mDotLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i]=new TextView(this.getContext());
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(context1.getResources().getColor(R.color.colorTransparentWhite));
            mDots[i].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            mDots[i].setGravity(1);

            mDotLayout.addView(mDots[i]);
        }
        if (mDots.length> 0){
            mDots[pos].setTextColor(context1.getResources().getColor(R.color.white));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
