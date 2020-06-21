package com.isober;

import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;

public class CalibrateFragment extends Fragment{

    private View rootView;

    private final long startTime = 2 * 1000;
    private final long interval = 1 * 100;

    private CountDownTimer cdTimer;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_calibrate, container, false);
        cdTimer = new MyCountDownTimer(startTime ,interval);
        cdTimer.start();

        rootView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent evnt) {
                return true;
            }
        });

        return rootView;

    }


    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onFinish() {
            getFragmentManager().beginTransaction().add(R.id.container, new TimerFragment()).commit();
            getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.container)).commit();
            getFragmentManager().executePendingTransactions();
            // TODO Auto-generated method stub

        }

    }
}