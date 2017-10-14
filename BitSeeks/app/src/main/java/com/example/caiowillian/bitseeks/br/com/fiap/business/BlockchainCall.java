package com.example.caiowillian.bitseeks.br.com.fiap.business;

import android.util.Log;

import com.example.caiowillian.bitseeks.br.com.fiap.models.DataMarket;
import com.example.caiowillian.bitseeks.br.com.fiap.utils.GetStringJSON;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Caio Willian on 12/10/2017.
 */

public class BlockchainCall {

    public BlockchainCall(){}

    public List<DataMarket> GetDataMarket(String result){
        String[] local = new String[]{"USD","AUD","BRL","CAD","CHF","CLP","CNY","DKK","EUR","GBP","HKD","INR","ISK","JPY","KRW","NZD","PLN","RUB","SEK","SGD","THB","TWD"};
        DataMarket dataMarket;
        List<DataMarket> list = new ArrayList<>();
        //DataMarket[] list = null;
        JSONObject json;
        JSONArray jsonArray;
        JSONObject item;
        if(result != null){
            try{

                for(int i = 0; i < local.length; i++){
                    dataMarket = new DataMarket();
                    json = new JSONObject(result);

                    json = json.getJSONObject(local[i]);

                    dataMarket.setLocal(local[i]);
                    dataMarket.setBuy(json.getDouble("buy"));
                    dataMarket.setLast(json.getDouble("last"));
                    dataMarket.setSell(json.getDouble("sell"));
                    dataMarket.setSymbol(json.getString("symbol"));
                    dataMarket.setNow(json.getDouble("15m"));

                    list.add(dataMarket);
                }
            }catch(Exception e){
                Log.i("Debug",e.getMessage());
            }
        }
        return list;
    }

    public String getDataMarketJSON(String... params){

        try{
            URL url = new URL("https://blockchain.info/ticker");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept","application/json");
            if(conn.getResponseCode() == 200)
                return GetStringJSON.GetJSON(conn);

        }catch(Exception e){
            Log.i("Debug",e.getMessage());
        }

        return null;
    }
}
