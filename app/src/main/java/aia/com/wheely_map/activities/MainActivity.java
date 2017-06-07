package aia.com.wheely_map.activities;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;

import aia.com.wheely_map.R;
import aia.com.wheely_map.fragments.MapViewFragment;

public class MainActivity extends FragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerListeners();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MapViewFragment fragment = new MapViewFragment();
        fragmentTransaction.add(R.id.map_container, fragment, TAG);
        fragmentTransaction.commit();
    }

    private void registerListeners() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_main);
        navigationView.setNavigationItemSelectedListener(this);
        fab.setOnClickListener(this);
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