package br.com.fiap.business;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Caio Willian on 08/10/2017.
 */

public class BlockchainCall {

    public BlockchainCall(){}

    public String getDataMarket(String... params){

        try{
            URL url = new URL("https://blockchain.info/ticker");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept","application/json");

            if(conn.getResponseCode() == 200){
                BufferedReader stream = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = "";
                StringBuilder response = new StringBuilder();
                while((line = stream.readLine()) != null)
                    response.append(line);

                conn.disconnect();
                return line.toString();
            }


        }catch(Exception e){}

        return null;
    }
}
