package com.nullgeodesic.washingtonhhh;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.nullgeodesic.washingtonhhh.listener.EventDetailAdapterViewClickListener;
import com.nullgeodesic.washingtonhhh.dto.Kennel;
import com.nullgeodesic.washingtonhhh.service.CommunicationController;
import com.nullgeodesic.washingtonhhh.service.ContentHolder;
import com.nullgeodesic.washingtonhhh.service.EventListAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String EXTRA_NO_NETWORK = "NO_NETWORK_AVAILABLE";

    private ListView listView;
    private EventListAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final boolean networkCallFailed = getIntent().getBooleanExtra(EXTRA_NO_NETWORK, false);

        final Toolbar toolbar = findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        this.swipeRefreshLayout = findViewById(R.id.content_event_swiperefresh);

        this.adapter = new EventListAdapter(this);
        this.listView = findViewById(R.id.content_event_list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new EventDetailAdapterViewClickListener(this));

        final MainActivity context = this;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CommunicationController.update(context);
            }
        });

        if(networkCallFailed) {
            warnNoNetwork();
        }
    }

    public void listUpdated() {
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    public void warnNoNetwork() {
        swipeRefreshLayout.setRefreshing(false);
        final Snackbar snackbar = Snackbar.make(listView, R.string.no_network_connection, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("close", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            final Intent intent = new Intent(this, SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra(SplashActivity.EXTRA_EXIT, true);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if(ContentHolder.kennelMap.isEmpty()) {
            drawer.closeDrawer(GravityCompat.START);
            return false;
        }
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
            case R.id.nav_kennel_ssss:
                kennelActivity(Kennel.SS_SHITSHOW);
                break;
//            case R.id.nav_contact_us:
//                informationalActivity("come help us out!", R.id.activity_informational_content_layout_help_us);
//                break;
        }

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
