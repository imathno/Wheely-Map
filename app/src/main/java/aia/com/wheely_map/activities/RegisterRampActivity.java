package aia.com.wheely_map.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import aia.com.wheely_map.R;
import aia.com.wheely_map.map.RampManager;

public class RegisterRampActivity extends AppCompatActivity
        implements View.OnClickListener,
        OnMapReadyCallback {

    public static final int REQUEST_IMAGE_CAPTURE = 1;

    private GoogleMap gMap;

    private Location currentLocation;
    private Bitmap imageBitmap;

    private EditText mDescriptionBox;
    private ImageView mImageView;
    private Button mRegisterRampButton;

    private boolean mLocationPermissionGranted = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_ramp);

        registerListeners();
    }

    private void registerListeners() {
        mDescriptionBox = (EditText) findViewById(R.id.edit_text_description);
        mImageView = (ImageView) findViewById(R.id.image_view_ramp);
        mRegisterRampButton = (Button) findViewById(R.id.button_register_ramp);

        mImageView.setOnClickListener(this);
        mRegisterRampButton.setOnClickListener(this);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }

    private void getDeviceLocation() {
        if (mLocationPermissionGranted && gMap != null) {
            currentLocation = gMap.getMyLocation();
        } else {
            Toast.makeText(this, "Something Broke", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_view_ramp : dispatchTakePictureIntent();
                break;
            case R.id.button_register_ramp : getDeviceLocation();
                buildRamp();
                this.finish();
                break;
        }
    }

    private void buildRamp() {
        if (currentLocation == null || imageBitmap == null) {
            errorHandler();
            return;
        }
        RampManager.registerRamp(mDescriptionBox.getText().toString(),
                currentLocation.getLatitude(),
                currentLocation.getLongitude());
    }

    private void errorHandler() {
        if (currentLocation == null) {
            Toast.makeText(this, "The location is invalid", Toast.LENGTH_SHORT).show();
        } else if (imageBitmap == null) {
            Toast.makeText(this, "Please set an image", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        gMap = map;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = false;
            return;
        }
    }
}
