package aia.com.wheely_map.fragments;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import aia.com.wheely_map.activities.OpenMarkerActivity;
import aia.com.wheely_map.map.Ramp;
import aia.com.wheely_map.map.RampManager;
import aia.com.wheely_map.user.UserManager;

import static aia.com.wheely_map.utils.ActivityUtils.openActivity;

public class MapsFragment extends MapFragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private static final String TAG = MapsFragment.class.getSimpleName();

    private static MapsFragment instance;
    private static List<Ramp> rampsOnMap;

    public GoogleMap mMap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rampsOnMap = new ArrayList<>();
        getMapAsync(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.d(TAG, "onMapReady GoogleMap googleMap:" + googleMap);
        setUpMap();
    }

    private void setUpMap() {
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(47.6062, -122)));
    }

    public boolean setMarker(Ramp ramp) {
        if (mMap == null) {
            Log.d(TAG, "setMarker mMap:" + mMap);
            return false;
        } else if (rampsOnMap.contains(ramp)) {
            Log.i(TAG, "setMarker: ramp is already marked");
            return false;
        }
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(ramp.getLatitude(), ramp.getLongitude())));
        rampsOnMap.add(ramp);
        return true;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        LatLng position = marker.getPosition();
        Ramp clickedMarker = RampManager.findRamp(position.latitude, position.longitude);
        if (clickedMarker != null) {
            openActivity(getContext(), OpenMarkerActivity.class);
        }
        return true;
    }

    public static MapsFragment getInstance() {
        Bundle args = new Bundle();

        if (instance == null) {
            instance = new MapsFragment();
        }

        instance.setArguments(args);
        return instance;
    }

    public GoogleMap getmMap() {
        return mMap;
    }
}
