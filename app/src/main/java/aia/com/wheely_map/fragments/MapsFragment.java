package aia.com.wheely_map.fragments;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

import aia.com.wheely_map.map.Ramp;
import aia.com.wheely_map.map.RampManager;

public class MapsFragment extends MapFragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private static final String TAG = MapsFragment.class.getSimpleName();

    private static MapsFragment instance;

    private GoogleMap mMap;

    private static List<Ramp> rampsOnMap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rampsOnMap = new ArrayList<>();
        getMapAsync(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        v.setVisibility(View.VISIBLE);
        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.d(TAG, "onMapReady GoogleMap googleMap:" + googleMap);

        setUpMap();

        for (Ramp ramp : RampManager.getToAddMarkerList()) {
            if (!rampsOnMap.contains(ramp)) {
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(ramp.getLatitude(), ramp.getLongitude())));
                rampsOnMap.add(ramp);
                rampsOnMap.add(ramp);
            }
        }
    }

    private void setUpMap() {
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(47.6062, -122.3321)));
        mMap.setOnMarkerClickListener(this);
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
        RampManager.findRamp(position.latitude, position.longitude);
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
}
