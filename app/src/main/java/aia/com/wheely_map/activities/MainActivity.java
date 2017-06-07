package aia.com.wheely_map.activities;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import aia.com.wheely_map.R;
import aia.com.wheely_map.fragments.MapViewFragment;
import aia.com.wheely_map.map.RampManager;
import aia.com.wheely_map.user.User;
import aia.com.wheely_map.user.UserManager;

public class MainActivity extends FragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private MapViewFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        User testUser = new User(true);
        UserManager.setLoggedInUser(testUser);

        RampManager.registerRamp("The White House", 47.6062, 122.3321);

        setContentView(R.layout.activity_main);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = new MapViewFragment();
        fragmentTransaction.add(R.id.map_container, fragment, TAG);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent activity;

        switch (item.getItemId()) {
            /*
            case R.id.nav_login : fragment.update();
                activity = new Intent(this, LoginActivity.class);
                break;
            */
            case R.id.nav_register_ramp : activity = new Intent(this, RegisterRampActivity.class);
                break;
            default : activity = null;
        }
        startActivity(activity);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}