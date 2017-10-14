package com.example.caiowillian.bitseeks;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DetailsActivity extends AppCompatActivity {

    private TextView lblTitle;
    private TextView lblBody;
    private TextView lblDescription;

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


        if(b != null){
            lblTitle.setText(b.getString("title"));
            lblDescription.setText(b.getString("description"));
            lblBody.setText(b.getString("body"));
        }



        Toast.makeText(this,b.getString("title").toString(), Toast.LENGTH_LONG).show();

    }

}
