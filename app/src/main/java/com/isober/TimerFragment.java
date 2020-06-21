package com.isober;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TimerFragment extends Fragment implements SensorEventListener{

    // Initialize Variables
    private CountDownTimer cdTimer;
    private View rootView;
    private boolean run_bit = false;
    private SenMan sm = new SenMan();
    private Sensor accel;
    private SensorManager senMan;
    // Variables for the clock
    private final long startTime = 5 * 1000;
    private final long interval = 1 * 100;
    // Old variables for the clock
    private long prevTime = 0;
    private long initialTime;

    MainActivity ma = (MainActivity) this.getActivity();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_timer, container, false);
        cdTimer = new MyCountDownTimer(startTime ,interval);
        senMan = (SensorManager) rootView.getContext().getSystemService(Context.SENSOR_SERVICE);
        accel = senMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senMan.registerListener(this, accel , SensorManager.SENSOR_DELAY_NORMAL);

        rootView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent evnt) {
                return true;
            }
        });
        //updateColor(Color.GREEN);
        cdTimer.start();
        run_bit = true;
        initialTime = System.currentTimeMillis();
        return rootView;
    }

    public void updateColor(int color){
        LinearLayout linearLayoutToUpdate = (LinearLayout) getView().findViewById(R.id.timer_ring);
        linearLayoutToUpdate.getBackground().setColorFilter(color, Mode.SRC_ATOP);
    }

    public void updateDisplayText(String s){
        TextView textViewToUpdate;
        textViewToUpdate = (TextView) getView().findViewById(R.id.textViewT);
        textViewToUpdate.setText(s);
        textViewToUpdate = (TextView) getView().findViewById(R.id.textViewB);
        textViewToUpdate.setText(s);
    }

    public void updateTimer(String s){
        TextView textViewToUpdate;
        textViewToUpdate = (TextView) getView().findViewById(R.id.textViewTimer);
        textViewToUpdate.setText(s);
    }

    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub
            updateTimer("" + (millisUntilFinished / 1000 + 1));
            updateColor(Color.GREEN);
        }

        @Override
        public void onFinish() {
            //updateColor(Color.RED);
            // TODO Auto-generated method stub

        }

    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSensorChanged(SensorEvent arg0) {
        // TODO Auto-generated method stub
        if((prevTime - initialTime) > 5000) {
            run_bit = false;

            Coordinate temp = sm.displayDifference();
            String temp2;
            if(temp.getSum() > 3.0) {
                temp2 = "It seems that you are drunk\nTaxi: 714-999-9999";
            } else {
                temp2 = "It seems that you are sober\nDrive home safely.";
            }
            MainActivity.res = temp2;
            getFragmentManager().beginTransaction().add(R.id.container, new ResultsFragment()).commit();
            getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.container)).commit();
//            getFragmentManager().executePendingTransactions();
            //change to resultsFragment
        }
        if (run_bit) {
            long current = System.currentTimeMillis();
            if((current - prevTime) > 100) {
                sm.getEvent(arg0);
                prevTime = current;
            }
        }
    }
    public void startOrStop(View v) {
        if(run_bit) {
            run_bit = false;
        } else {
            run_bit = true;
        }
    }

    public void onPause() {
        super.onPause();
        senMan.unregisterListener(this);
    }

    public void onResume() {
        super.onResume();
        senMan.registerListener(this, accel, SensorManager.SENSOR_DELAY_NORMAL);
    }

}
