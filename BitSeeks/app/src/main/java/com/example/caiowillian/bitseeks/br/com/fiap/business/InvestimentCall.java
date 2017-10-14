package com.example.caiowillian.bitseeks.br.com.fiap.business;

import android.util.Log;

import com.example.caiowillian.bitseeks.br.com.fiap.models.Investiment;
import com.example.caiowillian.bitseeks.br.com.fiap.utils.GetStringJSON;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Caio Willian on 12/10/2017.
 */

public class InvestimentCall {

    public List<Investiment> getInvestiment(String result){
        Log.i("Debug","GetInvestiment");
        Investiment investiment;
        List<Investiment> l = new ArrayList<Investiment>();
        JSONObject json;
        JSONArray array;
        if(result != null){
            try{
                Log.i("Debug","getInvestiment ------ ");
                //json = new JSONObject(result);
                //array = json.getJSONArray(result);
                array = new JSONArray(result);

                for(int i = 0; i < array.length(); i++){
                    json = array.getJSONObject(i);
                    investiment = new Investiment();
                    investiment.setId(json.getInt("Id"));
                    investiment.setDescription(json.getString("Description"));
                    investiment.setName(json.getString("Name"));
                    investiment.setStock(json.getInt("Stock"));
                    investiment.setValue(json.getDouble("Value"));
                    l.add(investiment);
                    investiment = null;
                }
            }catch(Exception e){
                Log.i("Debug","Error - getInvestiment: "+e.getStackTrace());
            }
        }

        return l;
    }

    public String getInvestimentJSON(String... params){
        try{
            URL url = new URL("http://testeprojetobrq08102017.azurewebsites.net/api/investimentapi/getinvestiment");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept","application/json");
            //conn.setRequestMethod("Accept","Application/json");
            if(conn.getResponseCode() == 200){

                return GetStringJSON.GetJSON(conn);
            }
        }catch(Exception e){
            Log.i("Debug","Error getInvestimentJSON -  "+e.getMessage());
        }

        return null;
    }
}
