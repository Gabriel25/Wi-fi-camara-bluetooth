package com.android1.gabriel.ejemplo;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 2;
    private static final int CALL_REQUEST_CODE =1 ;
    private static final int BLUETHOOT_REQUEST_CODE = 3;
    private Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
    }

    public void Call(View v) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity,new String[]{ Manifest.permission.CALL_PHONE},CALL_REQUEST_CODE);
            return;
        }
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:5534336697")));
    }

    public void EnableBluethoot(View v){

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.BLUETOOTH)) {



            } else {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.BLUETOOTH}, BLUETHOOT_REQUEST_CODE);
            }
            return;
        }


        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if(!bluetoothAdapter.enable()){
            Intent btIntetn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(btIntetn,BLUETHOOT_REQUEST_CODE);
        }
    }

    public void EnableCamera(View v) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {



            } else {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
            }
            return;
        }
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
         if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

        startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
        }
    }




    public boolean CheckPermission(String permission){
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), permission);
        if(result == PackageManager.PERMISSION_GRANTED){
            return true;
        }

        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case CAMERA_REQUEST_CODE:
                if (CheckPermission(Manifest.permission.CAMERA)) {



                } else {

                }
               break;
            case BLUETHOOT_REQUEST_CODE:
                break;
            case CALL_REQUEST_CODE:
                break;


        }
    }
}
