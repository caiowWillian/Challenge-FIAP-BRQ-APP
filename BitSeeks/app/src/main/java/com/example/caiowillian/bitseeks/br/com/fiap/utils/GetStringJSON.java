package com.example.caiowillian.bitseeks.br.com.fiap.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by Caio Willian on 12/10/2017.
 */

public class GetStringJSON {

    public static String GetJSON(HttpURLConnection conn) throws  Exception{
        String line = "";
        StringBuilder response = new StringBuilder();
        BufferedReader stream  = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        while((line = stream.readLine()) != null)
            response.append(line);

        conn.disconnect();

        return response.toString();
    }
}
