package com.example.caiowillian.bitseeks;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.caiowillian.bitseeks.br.com.fiap.business.OrderBookCall;
import com.example.caiowillian.bitseeks.br.com.fiap.component.CreateWalletDialog;
import com.example.caiowillian.bitseeks.br.com.fiap.component.ListAdapterOrderBook;
import com.example.caiowillian.bitseeks.br.com.fiap.component.MenuComponent;
import com.example.caiowillian.bitseeks.br.com.fiap.models.OrderBook;

import java.util.List;

public class OrderBookActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<OrderBook> l;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listView = (ListView) findViewById(R.id.list_item_order_book);

        new GetOrderBookAsync().execute();
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
        getMenuInflater().inflate(R.menu.order_book, menu);
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
            new CreateWalletDialog(OrderBookActivity.this).createDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        Intent it =  MenuComponent.actionMenuItem(OrderBookActivity.this,item.getItemId());

        if(it != null)
            startActivity(it);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class GetOrderBookAsync extends AsyncTask<String,Void,String>{
        private OrderBookCall orderBookCall;
        private Dialog progress;
        public GetOrderBookAsync(){ orderBookCall = new OrderBookCall(); }

        @Override
        protected void onPreExecute() { progress = ProgressDialog.show(OrderBookActivity.this,"Aguarde...","Carregando dados"); }

        @Override
        protected String doInBackground(String... s) {
            return orderBookCall.getDataMarketJSON(s);
        }

        @Override
        protected void onPostExecute(String s) {
            progress.dismiss();
            listView.setAdapter(new ListAdapterOrderBook(orderBookCall.getOrderBook(s),OrderBookActivity.this));
            super.onPostExecute(s);
        }
    }
}
