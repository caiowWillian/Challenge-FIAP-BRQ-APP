package com.example.caiowillian.bitseeks.br.com.fiap.business;

import android.util.Log;

import com.example.caiowillian.bitseeks.br.com.fiap.models.ImgNews;
import com.example.caiowillian.bitseeks.br.com.fiap.utils.GetStringJSON;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Caio Willian on 14/10/2017.
 */

public class ImgNewsCall {


    public ImgNews getImgNews(String result){
        List<ImgNews> l = new ArrayList<ImgNews>();
        ImgNews imgNews = null;
        JSONArray array;
        JSONObject json;


        if(result != null){
            try{
                json = new JSONObject(result);
                imgNews = new ImgNews();


                imgNews.setId(json.getInt("Id"));
                imgNews.setMimeType(json.getString("MimeType"));
                imgNews.setFileName(json.getString("FileName"));
                imgNews.setFileLenght(json.getInt("FileLenght"));
                imgNews.setFileContent(json.getString("FileContent").toString().getBytes());

                for(int i = 0; i < imgNews.getFileContent().length; i++)
                    Log.i("Debug","bytes - "+imgNews.getFileContent()[i]);


            }catch(Exception e){}
        }

        return imgNews;
    }

    public String getImgJSON(String... params){
        try{
            Log.i("Debug","getImgJSON");
            URL url = new URL("http://bitseeks.azurewebsites.net/api/imgnewsapi/getimgnews?id="+params[0]);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();



            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept","application/json");

            if(conn.getResponseCode() == 200)
                return GetStringJSON.GetJSON(conn);

        }catch(Exception e){
            Log.i("GetImgJSON","Error getInvestimentJSON -  "+e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}
