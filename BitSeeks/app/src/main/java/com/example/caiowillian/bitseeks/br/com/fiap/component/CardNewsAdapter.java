package com.example.caiowillian.bitseeks.br.com.fiap.component;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.caiowillian.bitseeks.R;
import com.example.caiowillian.bitseeks.br.com.fiap.models.News;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Caio Willian on 14/10/2017.
 */

public class CardNewsAdapter extends RecyclerView.Adapter<CardNewsAdapter.ViewHolder> {
    private List<News> l;

    public CardNewsAdapter(List<News> l){
        this.l = l;
    }

    @Override
    public CardNewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_data_news,parent,false);

        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(CardNewsAdapter.ViewHolder holder, int position) {
        holder.teste.setText(l.get(position).getTitle());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView teste;
        public ViewHolder(View v) {
            super(v);
            teste = (TextView) v.findViewById(R.id.teste);

        }
    }

    @Override
    public int getItemCount() {
        return l.size();
    }
}
