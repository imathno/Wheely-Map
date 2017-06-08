package aia.com.wheely_map.activities;


import android.app.FragmentManager;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.MapFragment;

import aia.com.wheely_map.R;
import aia.com.wheely_map.fragments.MapsFragment;
import aia.com.wheely_map.map.Ramp;
import aia.com.wheely_map.map.RampManager;
import aia.com.wheely_map.user.User;
import aia.com.wheely_map.user.UserManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private MapFragment mMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        User user = new User();
        UserManager.setLoggedInUser(user);

        RampManager.registerRamp("TEST", 47.6062, -122);
        RampManager.registerRamp("TEST", 47.6062, -150);

        FragmentManager fragmentManager = getFragmentManager();
        mMapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map_frag);

        setContentView(R.layout.activity_main);

        registerListeners();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setUpMapFragment();
    }

    private void setUpMapFragment() {
        if (mMapFragment == null) {
            mMapFragment = MapsFragment.newInstance();
        }
        getFragmentManager().beginTransaction()
                .add(R.id.map_container, mMapFragment)
                .commit();
    }

    private void registerListeners() {
        Log.d(TAG, "registerListeners:Registering Listeners");
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_main);
        navigationView.setNavigationItemSelectedListener(this);
        fab.setOnClickListener(this);
        Log.d(TAG, "registerListeners:Listeners Registered");
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
       switch (item.getItemId()) {
           case R.id.nav_login:
               openActivity(this, LoginActivity.class);
               break;
       }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_main : openActivity(this, RegisterRampActivity.class);
                break;
        }
    }

    private void openActivity(Context context, Class<?> cls) {
        Intent activity = new Intent(context, cls);
        startActivity(activity);
    }
}