package com.nullgeodesic.washingtonhhh;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nullgeodesic.washingtonhhh.click_listener.EventDetailAdapterViewClickListener;
import com.nullgeodesic.washingtonhhh.dto.HashEventDto;
import com.nullgeodesic.washingtonhhh.dto.Kennel;
import com.nullgeodesic.washingtonhhh.service.ContentHolder;
import com.nullgeodesic.washingtonhhh.service.EventListAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final ListView listView = (ListView) findViewById(R.id.content_event_list_view);
        final ArrayAdapter<HashEventDto> arrayAdapter = new EventListAdapter(this, R.layout.content_event_list_item_no_kennel, R.id.list_item_name_textview, ContentHolder.allEvents);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new EventDetailAdapterViewClickListener(this));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            final Intent intent = new Intent(this, SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_kennel_puget_sound:
                kennelActivity(Kennel.PUGET_SOUND);
                break;
            case R.id.nav_kennel_no_balls:
                kennelActivity(Kennel.NO_BALLS);
                break;
            case R.id.nav_kennel_seattle:
                kennelActivity(Kennel.SEATTLE);
                break;
            case R.id.nav_kennel_rain_city:
                kennelActivity(Kennel.RAIN_CITY);
                break;
            case R.id.nav_kennel_hswtf:
                kennelActivity(Kennel.HSWTF);
                break;
            case R.id.nav_kennel_seamon:
                kennelActivity(Kennel.SEAMON);
                break;
            case R.id.nav_kennel_tacoma:
                kennelActivity(Kennel.TACOMA);
                break;
//            case R.id.nav_contact_us:
//                informationalActivity("come help us out!", R.id.activity_informational_content_layout_help_us);
//                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void kennelActivity(final String kennel) {
        final Intent intent = new Intent(this, KennelActivity.class);
        intent.putExtra(KennelActivity.KENNEL_EXTRA, kennel);
        startActivity(intent);
    }

    private void informationalActivity(final String title, final int target) {
        final Intent intent = new Intent(this, InformationalActivity.class);
        intent.putExtra(InformationalActivity.INFORMATIONAL_TITLE_EXTRA, title);
        intent.putExtra(InformationalActivity.INFORMATIONAL_TARGET_EXTRA, target);
        startActivity(intent);
    }
}
