package com.example.caiowillian.bitseeks.br.com.fiap.component;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.caiowillian.bitseeks.R;
import com.example.caiowillian.bitseeks.br.com.fiap.models.DataMarket;


import java.util.List;

/**
 * Created by Caio Willian on 12/10/2017.
 */

public class ListAdapterCurrency extends BaseAdapter{
    private final List<DataMarket> list;
    private final Activity act;
    public ListAdapterCurrency(List<DataMarket> list, Activity act){
        this.list = list;
        this.act = act;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = act.getLayoutInflater().inflate(R.layout.list_data_markert,viewGroup,false);
        DataMarket markert = list.get(i);

        TextView lblName = (TextView) v.findViewById(R.id.lblName);
        TextView lblBuy = (TextView) v.findViewById(R.id.lblbuy);
        TextView lblSell = (TextView) v.findViewById(R.id.lblSell);

        lblName.setText(markert.getLocal());
        lblBuy.setText("Buy " + markert.getSymbol()+" "+markert.getBuy());
        lblSell.setText("Sell " + markert.getSymbol()+" "+markert.getSell());
        return v;
    }
}
