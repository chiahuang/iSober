package com.isober;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SenActivity extends Activity implements SensorEventListener {

    private Sensor accel;
    private SensorManager senMan;

    private float x = 0;
    private float y = 0;
    private float z = 0;

    private long prevTime;
    private TextView box;

    private Container listOfPoints;

    private boolean runOrNot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sensor);
        // control the sensors
        senMan = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accel = senMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senMan.registerListener(this, accel , SensorManager.SENSOR_DELAY_NORMAL);


        // change the textView to add more points
        box = (TextView) findViewById(R.id.textView1);
        if (box == null) {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        }
        box.setMovementMethod(new ScrollingMovementMethod());
        box.setText("Testing");

        listOfPoints = new Container();

        runOrNot = false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
        // TODO Auto-generated method stub

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        // only updates when clicked
        // need to consider time when constantly updating
        if(runOrNot) {
            Sensor mySensor = event.sensor;
            if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                long current = System.currentTimeMillis();

                if((current - prevTime) > 100) {
                    prevTime = current;
                    x = event.values[0];
                    y = event.values[1];
                    z = event.values[2];
                    String text = "x: " + x + " y: " + y + " z: " + z;

                    listOfPoints.append(new Coordinate(x,y,z));
                    box.setText(text + "\n" + box.getText().toString());
                }

            }
        }

    }
    // useless function
    public void displayPosition(View v) {
        return;

    }

    public void displayAverage(View v) {
        Coordinate aver = listOfPoints.average();
        box.setText(aver.text() + " " + aver.getSum() + "\n" + box.getText().toString());
    }

    public void displayDifference(View v) {
        Coordinate dif = listOfPoints.difference();
        box.setText(dif.text() + " " + dif.getSum() + "\n" + box.getText().toString());
    }

    public void displayClear(View v) {
        box.setText("");
        listOfPoints.empty();
    }

    public void startOrStop(View v) {
        if(runOrNot) {
            runOrNot = false;
        } else {
            runOrNot = true;
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        senMan.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        senMan.registerListener(this, accel, SensorManager.SENSOR_DELAY_NORMAL);
    }

}
