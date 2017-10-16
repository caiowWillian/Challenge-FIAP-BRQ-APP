package com.example.caiowillian.bitseeks.br.com.fiap.component;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.example.caiowillian.bitseeks.DetailsActivity;
import com.example.caiowillian.bitseeks.R;
import com.example.caiowillian.bitseeks.br.com.fiap.models.News;

import java.util.List;

/**
 * Created by Caio Willian on 14/10/2017.
 */

public class CardNewsAdapter extends RecyclerView.Adapter<CardNewsAdapter.ViewHolder> {
    private List<News> l;
    private Context ctx;
    public CardNewsAdapter(List<News> l, Context ctx){
        this.l = l;
        this.ctx = ctx;
    }

    @Override
    public CardNewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_data_news,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CardNewsAdapter.ViewHolder holder, final int position) {
        holder.lblDtPost.setText("Postado em: "+l.get(position).getDataCadastro());
        holder.lblTitle.setText(l.get(position).getTitle());
        holder.lblDescription.setText(l.get(position).getDescription());
        Log.i("This","onBind - antes"+position);

        if(l.get(position).getImgNews() != null){

            try{
                Log.i("This","onBind");
                byte[] decodeString = Base64.decode(l.get(position).getImgNews().getBase64().split(",")[1],Base64.DEFAULT);
                holder.imgView.setImageBitmap(BitmapFactory.decodeByteArray(decodeString,0,decodeString.length));
            }catch(Exception e){}
        }

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                b.putString("title",l.get(position).getTitle());
                b.putString("description",l.get(position).getDescription());
                b.putString("body",l.get(position).getBody());
                b.putString("dataCadastro",l.get(position).getDataCadastro());
                b.putInt("id",l.get(position).getId());


                if(l.get(position).getImgNews() != null) {
                    Log.i("Debug", "This, is img is not null");
                    b.putString("img","");
                }else{

                }
                Intent it = new Intent(ctx,DetailsActivity.class);
                it.putExtras(b);

                view.getContext().startActivity(it);
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView lblDtPost;
        public TextView lblTitle;
        public TextView lblDescription;
        public BootstrapButton btn;
        public ImageView imgView;
        public ViewHolder(View v) {
            super(v);
            lblDtPost = (TextView) v.findViewById(R.id.lblDtPost);
            lblTitle = (TextView) v.findViewById(R.id.lblTitle);
            lblDescription = (TextView) v.findViewById(R.id.lblDescription);
            btn = (BootstrapButton) v.findViewById(R.id.button);
            imgView = (ImageView) v.findViewById(R.id.img_news);
        }
    }

    @Override
    public int getItemCount() {
        return l.size();
    }
}
