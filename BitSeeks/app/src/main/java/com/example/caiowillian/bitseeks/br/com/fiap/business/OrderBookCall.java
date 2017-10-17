package com.example.caiowillian.bitseeks.br.com.fiap.business;

import android.util.Log;
import com.example.caiowillian.bitseeks.br.com.fiap.models.OrderBook;
import com.example.caiowillian.bitseeks.br.com.fiap.utils.GetStringJSON;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Caio Willian on 17/10/2017.
 */

public class OrderBookCall {

    public List<OrderBook> getOrderBook(String result){
        List<OrderBook> l = new ArrayList<OrderBook>();
        JSONObject json;
        JSONArray jsonArray;
        JSONArray realArrayJson;
        if(result != null){
            try{
                json = new JSONObject(result);
                jsonArray = json.getJSONArray("bids");
                for(int i = 0; i < jsonArray.length(); i++){
                    realArrayJson = jsonArray.getJSONArray(i);
                    l.add(new OrderBook(realArrayJson.get(0).toString(),Double.parseDouble(realArrayJson.get(1).toString())));
                }
            }catch(Exception e){
                Log.i("Debug",e.getMessage());
            }
        }
        return l;
    }

    public String getDataMarketJSON(String... params){
        try{
            URL url = new URL("https://api.bitvalor.com/v1/order_book.json");
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
