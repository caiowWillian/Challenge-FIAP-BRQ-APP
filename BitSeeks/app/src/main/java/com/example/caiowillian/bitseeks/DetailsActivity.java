package com.example.caiowillian.bitseeks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

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

            String img = b.getString("img");

            Toast.makeText(DetailsActivity.this,img+"img",Toast.LENGTH_LONG).show();
        }
    }

}
