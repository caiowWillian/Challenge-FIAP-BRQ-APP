package com.example.caiowillian.application;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.caiowillian.application.br.com.fiap.business.BlockchainCall;
import com.example.caiowillian.application.br.com.fiap.models.DataMarket;
import com.example.caiowillian.myapplication.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetDataMarket task = new GetDataMarket();
        task.execute();
    }

    private class GetDataMarket extends AsyncTask<String,Void,String> {

        private BlockchainCall blockchainCall;

        public GetDataMarket(){
            blockchainCall = new BlockchainCall();
        }

        @Override
        protected String doInBackground(String... params) {
            return blockchainCall.getDataMarketJSON(params);
        }

        @Override
        protected void onPostExecute(String s) {
            List<DataMarket> market = blockchainCall.GetDataMarket(s);
            String result = "";
            for(int i = 0; i < market.size(); i++)
                result += market.get(i).getSymbol();


            //result = market.get(0).getSymbol();

            Toast.makeText(MainActivity.this,result,Toast.LENGTH_LONG).show();

            super.onPostExecute(s);
        }
    }
}
