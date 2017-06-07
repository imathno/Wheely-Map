package aia.com.wheely_map.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Map;

import aia.com.wheely_map.R;
import aia.com.wheely_map.activities.OpenMarkerActivity;
import aia.com.wheely_map.map.RampManager;
import aia.com.wheely_map.map.Ramp;

public class MapViewFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private static final String TAG = MapViewFragment.class.getSimpleName();

    private GoogleMap mMap;

    private static Map<Marker, Ramp> markerMap = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);

        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.addMarker(new MarkerOptions().position(new LatLng(47.6062, -122.3321)));
        mMap.setOnMarkerClickListener(this);
    }

    public void update() {
        for (Ramp addRamp : RampManager.getToAddMarkerList()) {
            Marker newMarker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(addRamp.getLatitude(), addRamp.getLongitude())));
            markerMap.put(newMarker, addRamp);
            RampManager.getToAddMarkerList().remove(addRamp);
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
            Intent openMarker = new Intent(getContext(), OpenMarkerActivity.class);
            openMarker.putExtra("lat", 47.6062);
            openMarker.putExtra("long", 122.3321);
            openMarker.putExtra("description", "What's good cuzzo");
            startActivity(openMarker);
            return true;
    }
}
