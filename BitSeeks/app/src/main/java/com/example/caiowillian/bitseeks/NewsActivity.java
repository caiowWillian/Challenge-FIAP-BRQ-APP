package com.example.caiowillian.bitseeks;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.Toast;

import com.example.caiowillian.bitseeks.br.com.fiap.business.ImgNewsCall;
import com.example.caiowillian.bitseeks.br.com.fiap.business.NewsCall;
import com.example.caiowillian.bitseeks.br.com.fiap.component.CardNewsAdapter;
import com.example.caiowillian.bitseeks.br.com.fiap.component.CreateWalletDialog;
import com.example.caiowillian.bitseeks.br.com.fiap.component.MenuComponent;
import com.example.caiowillian.bitseeks.br.com.fiap.models.ImgNews;
import com.example.caiowillian.bitseeks.br.com.fiap.models.News;

import java.util.ArrayList;
import java.util.List;

import info.blockchain.api.wallet.Wallet;

public class NewsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView mRecycleView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private CardNewsAdapter adapter;
    private List<News> listNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mRecycleView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecycleView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(mLayoutManager);

        new GetNews().execute();

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
        getMenuInflater().inflate(R.menu.news, menu);
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
            new CreateWalletDialog(NewsActivity.this).createDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent it = MenuComponent.actionMenuItem(NewsActivity.this,id);

        if(it != null){
            startActivity(it);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class GetImgNewsAsync extends AsyncTask<String,Void,String>{
        private ImgNewsCall imgNewsCall;
        private int position;
        public GetImgNewsAsync(int position){
            imgNewsCall = new ImgNewsCall();
            this.position = position;
        }

        @Override
        protected void onPostExecute(String s) {
            ImgNews imgNews = imgNewsCall.getImgNews(s);

            imgNews = imgNewsCall.getImgNews(s);

            listNews.get(position).setImgNews(imgNews);
            Log.i("Debug","Entry - 1" + imgNews.getBase64());

            adapter.notifyDataSetChanged();
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... params) {
            Log.i("Testando","aiaiaiaiaiaiaiiaia");

            String result = imgNewsCall.getImgJSON(params);
            if(result != null)
                Log.i("encontrou?",result);
            else
                Log.i("encontrou?","Não");
            return result;
        }
    }

    private class GetNews extends AsyncTask<String,Void,String>{
        private NewsCall newsCall;
        private ProgressDialog progress;

        public GetNews(){
            newsCall = new NewsCall();
        }

        @Override
        protected void onPreExecute(){
            progress = ProgressDialog.show(NewsActivity.this,"Aguarde...","Carregando noticias");
        }

        @Override
        protected void onPostExecute(String s) {
            progress.dismiss();

            listNews = newsCall.getNews(s);
            adapter = new CardNewsAdapter(listNews,NewsActivity.this);
            mRecycleView.setAdapter(adapter);

            for(int i = 0; i < listNews.size(); i++)
                new GetImgNewsAsync(i).execute(Integer.toString(listNews.get(i).getId()));
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... params) {
            return newsCall.getInvestimentJSON(params);
        }
    }
}
