package com.example.caiowillian.bitseeks;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.caiowillian.bitseeks.br.com.fiap.business.BlockchainCall;
import com.example.caiowillian.bitseeks.br.com.fiap.component.ListAdapterCurrency;
import com.example.caiowillian.bitseeks.br.com.fiap.component.MenuComponent;
import com.example.caiowillian.bitseeks.br.com.fiap.models.DataMarket;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.list_item);

        /*]
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        GetDataMarket task = new GetDataMarket();
        task.execute();
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent it = MenuComponent.actionMenuItem(MainActivity.this,id);

        if(it != null)
            startActivity(it);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private class GetDataMarket extends AsyncTask<String,Void,String> {

        private BlockchainCall blockchainCall;
        private ProgressDialog progress;

        public GetDataMarket(){
            blockchainCall = new BlockchainCall();
        }

        @Override
        protected void onPreExecute(){
            progress = ProgressDialog.show(MainActivity.this,"Aguarde...","Carregando dados");
        }

        @Override
        protected String doInBackground(String... params) {
            return blockchainCall.getDataMarketJSON(params);

            //return investimentCall.getInvestimentJSON(params);
        }

        @Override
        protected void onPostExecute(String s) {
            List<DataMarket> market = blockchainCall.GetDataMarket(s);
            //List<Investiment> investiment = investimentCall.getInvestiment(s);
            String result = "";
            //for(int i = 0; i < market.size(); i++)
                //result += investiment.get(0).getName();



            listView.setAdapter(new ListAdapterCurrency(market,MainActivity.this));

            //result = market.get(0).getSymbol();

            //Toast.makeText(MainActivity.this,market.get(0).getLocal(),Toast.LENGTH_LONG).show();

            progress.dismiss();

            super.onPostExecute(s);
        }
    }
}

