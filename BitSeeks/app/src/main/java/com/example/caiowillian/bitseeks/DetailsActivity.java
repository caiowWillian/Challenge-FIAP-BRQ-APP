package com.example.caiowillian.bitseeks;

import android.app.ProgressDialog;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caiowillian.bitseeks.br.com.fiap.business.ImgNewsCall;
import com.example.caiowillian.bitseeks.br.com.fiap.models.ImgNews;

public class DetailsActivity extends AppCompatActivity {

    private TextView lblTitle;
    private TextView lblBody;
    private TextView lblDescription;
    private ImageView img;
    private ImgNewsCall imgNewsCall;
    private int idNews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle b = getIntent().getExtras();

        lblTitle = (TextView) findViewById(R.id.lblTitle);
        lblBody = (TextView) findViewById(R.id.lblBody);
        lblDescription = (TextView) findViewById(R.id.lblDescription);
        img = (ImageView) findViewById(R.id.img);

        if(b != null){
            lblTitle.setText(b.getString("title"));
            lblDescription.setText(b.getString("description"));
            lblBody.setText(b.getString("body"));
            idNews = b.getInt("id");
        }
        new GetImgNewsAsync().execute(Integer.toString(idNews));
    }

    private class GetImgNewsAsync extends AsyncTask<String,Void,String> {
        private ProgressDialog progress;

        public GetImgNewsAsync(){ imgNewsCall = new ImgNewsCall(); }

        @Override
        protected void onPreExecute(){ /*progress = ProgressDialog.show(DetailsActivity.this,"Aguarde...","Carregando noticias");*/ }

        @Override
        protected void onPostExecute(String s) {
            //progress.dismiss();
            ImgNews imgNews = imgNewsCall.getImgNews(s);
            Log.i("Debug","onPostExecute");
            if(imgNews != null)
                img.setImageBitmap(imgNews.getBitMapImage());

            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... params) { return imgNewsCall.getImgJSON(params); }
    }
}
