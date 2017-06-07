package aia.com.wheely_map.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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

    private GoogleMap mMap;

    private SupportMapFragment mapFragment;

    private static Map<Marker, Ramp> markerMap = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);

        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);
    }

    public void update() {
        mapFragment.getMapAsync(this);
        for (Ramp addRamp : RampManager.getToAddMarkerList()) {
            Marker newMarker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(addRamp.getLatitude(), addRamp.getLongitude())));
            markerMap.put(newMarker, addRamp);
            RampManager.getToAddMarkerList().remove(addRamp);
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Ramp clickedRamp = markerMap.get(marker);

        if (clickedRamp != null) {
            Intent openMarker = new Intent(getContext(), OpenMarkerActivity.class);
            openMarker.putExtra("lat", clickedRamp.getLatitude());
            openMarker.putExtra("long", clickedRamp.getLongitude());
            openMarker.putExtra("description", clickedRamp.getDescription());
            openMarker.putExtra("rampImage", clickedRamp.getRampImage());
            startActivity(openMarker);
            return true;
        }
        return false;
    }
}
