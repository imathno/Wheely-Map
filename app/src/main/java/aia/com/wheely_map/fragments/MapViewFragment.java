package aia.com.wheely_map.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import aia.com.wheely_map.R;
import aia.com.wheely_map.map.RampManager;
import aia.com.wheely_map.map.Ramp;

public class MapViewFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;

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
        mMap.setOnMarkerClickListener(this);
        List<Ramp> toAddMarker = RampManager.getToAddMarkerList();
        for (Ramp addRamp : toAddMarker) {
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(addRamp.getLatitude(), addRamp.getLongitude())));
            RampManager.getToAddMarkerList().remove(addRamp);
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        LatLng position = marker.getPosition();
        RampManager.findRamp(position.latitude, position.longitude);
        return true;
    }
}
