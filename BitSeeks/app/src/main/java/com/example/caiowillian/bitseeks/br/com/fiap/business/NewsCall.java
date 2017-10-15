package com.example.caiowillian.bitseeks.br.com.fiap.business;

import android.os.AsyncTask;
import android.util.Log;

import com.example.caiowillian.bitseeks.br.com.fiap.models.ImgNews;
import com.example.caiowillian.bitseeks.br.com.fiap.models.Investiment;
import com.example.caiowillian.bitseeks.br.com.fiap.models.News;
import com.example.caiowillian.bitseeks.br.com.fiap.utils.GetStringJSON;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Caio Willian on 13/10/2017.
 */

public class NewsCall {


    public NewsCall(){}

    public List<News> getNews(String result){
        List<News> l = new ArrayList<News>();
        News news;
        ImgNews imgNews = null;
        JSONObject json;
        JSONArray array;
        if(result != null){
            try{
                array = new JSONArray(result);

                for(int i = 0; i < array.length(); i++){
                    json = array.getJSONObject(i);

                    news = new News();

                    news.setDescription(json.getString("Description"));
                    news.setId(json.getInt("Id"));
                    news.setBody(json.getString("Body"));
                    news.setDataAlteracao(json.getString("DataAlteracao"));
                    news.setDataCadastro(json.getString("DataCadastro"));
                    news.setTitle(json.getString("Title"));

                    //array = json.getJSONArray("ImgNews");

                    /*
                    for(int j = 0; j < array.length(); j++){
                        json = array.getJSONObject(j);

                        imgNews = new ImgNews();
                        imgNews.setId(json.getInt("Id"));
                        imgNews.setFileLenght(json.getInt("FileLenght"));
                        imgNews.setFileName(json.getString("FileName"));
                        imgNews.setMimeType(json.getString("MimeType"));
                        //imgNews.setFileContent();

                    }
                    */


                    //ImgNewsCall im = new ImgNewsCall();

                    //String rs = im.getImgJSON(Integer.toString(news.getId()));
                    //news.setImgNews(im.getImgNews(rs));

                    //Log.i("Debug","Img Async - "+rs);


                    //GetImgAsync getImgAsync = new GetImgAsync();
                    //getImgAsync.execute(Integer.toString(news.getId()));
                    //news.setImgNews(getImgAsync.getImgNews());

                    //if(imgNews != null)
                        //news.setImgNews(imgNews);

                    l.add(news);
                    news = null;
                }
            }catch(Exception e){}
        }
        return l;
    }

    public String getInvestimentJSON(String... params){
        try{
            URL url = new URL("http://bitseeks.azurewebsites.net/api/newsapi/getnews");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept","application/json");
            //conn.setRequestMethod("Accept","Application/json");
            if(conn.getResponseCode() == 200)
                return GetStringJSON.GetJSON(conn);

        }catch(Exception e){
            Log.i("Debug","Error getInvestimentJSON -  "+e.getMessage());
        }

        return null;
    }

    private class GetImgAsync extends AsyncTask<String,Void,String>{
        private ImgNewsCall imgNewsCall;
        private ImgNews imgNews;

        public ImgNews getImgNews(){
            return imgNews;
        }

        public GetImgAsync(){
            imgNewsCall = new ImgNewsCall();
        }

        @Override
        protected void onPostExecute(String s) {
            imgNews = imgNewsCall.getImgNews(s);
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... params) {
            return imgNewsCall.getImgJSON(params);
        }
    }
}
