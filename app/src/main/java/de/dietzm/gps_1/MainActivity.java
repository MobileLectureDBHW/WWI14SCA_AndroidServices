package de.dietzm.gps_1;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.PowerManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Set;

public class MainActivity extends AppCompatActivity implements LocationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null  || !mBluetoothAdapter.isEnabled()) {

        } else {
            Set<BluetoothDevice> devices = mBluetoothAdapter.getBondedDevices();
            for(int i = 0; i < devices.size(); i++){
                BluetoothDevice device = (BluetoothDevice)devices.toArray()[i];

            }
        }


    }

    @Override
    protected void onStart() {
        super.onStart();

        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        locationManager
                .requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 100, this);

    }

    @Override
    public void onLocationChanged(Location location) {
        System.out.println(location.getLongitude() + " - " + location.getLatitude());
    }


    public void startGame(View view){

        PowerManager powMan = (PowerManager) getSystemService(Context.POWER_SERVICE);
        powMan.reboot("TEST");

        Intent i = new Intent(getApplicationContext(), GameActivity.class);
        startActivity(i);
    }












    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
