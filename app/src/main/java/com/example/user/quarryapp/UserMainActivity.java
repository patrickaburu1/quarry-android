package com.example.user.quarryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.user.quarryapp.Fragments.HomeUser;
import com.example.user.quarryapp.Fragments.PostExpenses;
import com.example.user.quarryapp.Fragments.Products;
import com.example.user.quarryapp.Fragments.Sales;
import com.example.user.quarryapp.Fragments.Sell;

public class UserMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //global variable
    public static String sell_p_name,sell_p_id,sell_p_price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //load home fragment
        FragmentTransaction f=getSupportFragmentManager().beginTransaction();
        f.replace(R.id.mainUserContent, new HomeUser(),"HOME");
        f.commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            // Handle the camera action
            Intent i = new Intent(UserMainActivity.this,AdminMainActivity.class);
            startActivity(i);
        } else if (id == R.id.sell) {
            FragmentTransaction f=getSupportFragmentManager().beginTransaction();
            f.replace(R.id.mainUserContent, new Products(),"SELL");
            f.commit();

        } else if (id == R.id.dailysale) {
            FragmentTransaction f=getSupportFragmentManager().beginTransaction();
            f.replace(R.id.mainUserContent, new Sales(),"SALES");
            f.commit();

        } else if (id == R.id.expenses) {
            FragmentTransaction f=getSupportFragmentManager().beginTransaction();
            f.replace(R.id.mainUserContent, new PostExpenses(),"SALES");
            f.commit();
        }
//        else if (id == R.id.) {
//
//        } else if (id == R.id.) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void goTOSell(String id,  String price, String name){
        sell_p_id=id;
        sell_p_name=name;
        sell_p_price=price;
        FragmentTransaction f=getSupportFragmentManager().beginTransaction();
        f.replace(R.id.mainUserContent, new Sell(),"SELL");
        f.commit();
    }
}
