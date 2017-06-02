package aia.com.wheely_map.activities;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.Toast;

import aia.com.wheely_map.R;
import aia.com.wheely_map.fragments.MapViewFragment;
import aia.com.wheely_map.map.MapManager;
import aia.com.wheely_map.map.Ramp;
import aia.com.wheely_map.user.User;

public class MainActivity extends FragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MapViewFragment fragment = new MapViewFragment();
        fragmentTransaction.add(R.id.map_container, fragment, TAG);
        fragmentTransaction.commit();
    }

    //THIS BROKEN GET HAMMER AND FIX IT
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        Intent activity;

        if (id == R.id.nav_login) {
            activity = new Intent(this, LoginActivity.class);
        } else {
            activity = null;
        }

        startActivity(activity);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}