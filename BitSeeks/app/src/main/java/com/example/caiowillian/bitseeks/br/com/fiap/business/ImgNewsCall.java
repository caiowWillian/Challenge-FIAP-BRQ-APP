package com.example.caiowillian.bitseeks.br.com.fiap.business;

import android.util.Base64;
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
        //List<ImgNews> l = new ArrayList<ImgNews>();
        ImgNews imgNews = null;
        JSONArray array;
        JSONObject json;


        imgNews = new ImgNews();
        imgNews.setBase64("data:image/png;base64,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");

        if(result != null){
            try{
                json = new JSONArray(result).getJSONObject(0);
                //json = new JSONObject(result);
                //imgNews = new ImgNews();

                imgNews.setId(json.getInt("Id"));
                imgNews.setMimeType(json.getString("MimeType"));
                imgNews.setFileName(json.getString("FileName"));
                imgNews.setFileLenght(json.getInt("FileLenght"));
                imgNews.setBase64(json.getString("Base64"));
                imgNews.setNewsId(json.getInt("IdNews"));
                imgNews.setFileContent(json.getString("FileContent").toString().getBytes());


                for(int i = 0; i < imgNews.getFileContent().length; i++)
                    Log.i("Debug","bytes - "+imgNews.getFileContent()[i]);


            }catch(Exception e){
                Log.i("Crach","Olha isso - "+e.getStackTrace());
            }
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
