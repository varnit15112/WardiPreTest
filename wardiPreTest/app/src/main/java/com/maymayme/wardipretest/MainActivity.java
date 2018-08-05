package com.maymayme.wardipretest;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends Activity implements SensorEventListener {


    private TextView text;
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;

    float x1=-100;
    float y1=-100;
    float z1=-100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.mainText);

        text.setText("Don’t you get bored of me");

        senSensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            if (x1==-100){
                x1=x;
            }
            if (y1==-100){
                y1=y;
            }
            if (z1==-100){
                z1=z;
            }

            if ( Math.pow((x1-x),2) + Math.pow((y1-y),2) + Math.pow((z1-z),2) < 50 ){
                text.setText("Don’t you get bored of me");
            }else{
                text.setText("See, I told you");
            }

            System.out.println( Math.pow((x1-x),2) + Math.pow((y1-y),2) + Math.pow((z1-z),2)  ) ;

            //System.out.println(Float.toString(x)+","+Float.toString(y)+","+Float.toString(z));

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
