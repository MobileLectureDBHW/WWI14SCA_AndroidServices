package de.dietzm.gps_1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

public class GameActivity extends AppCompatActivity implements SensorEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> deviceSensors = sm .getSensorList(Sensor.TYPE_ALL);

        for(int i = 0; i < deviceSensors.size(); i++){
            Sensor sensor = deviceSensors.get(i);
            System.out.println(sensor.getName());
        }

        Sensor s1 = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sm.registerListener(this, s1, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public void takePicture(View view){

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 1);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 1 && resultCode == RESULT_OK){

            Bundle extras = data.getExtras();
            Bitmap image = (Bitmap) extras.get("data");
            ImageView imv = (ImageView) findViewById(R.id.imageView);
            imv.setImageBitmap(image);

        }

    }
}
