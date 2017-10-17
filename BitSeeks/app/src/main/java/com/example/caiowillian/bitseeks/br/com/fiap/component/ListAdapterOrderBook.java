package com.example.caiowillian.bitseeks.br.com.fiap.component;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.caiowillian.bitseeks.R;
import com.example.caiowillian.bitseeks.br.com.fiap.models.DataMarket;
import com.example.caiowillian.bitseeks.br.com.fiap.models.OrderBook;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Caio Willian on 17/10/2017.
 */

public class ListAdapterOrderBook extends BaseAdapter {
    private List<OrderBook> list;
    private final Activity act;

    public ListAdapterOrderBook(List<OrderBook> list, Activity act){
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
        View v = act.getLayoutInflater().inflate(R.layout.list_order_book,viewGroup,false);
        OrderBook orderBook = list.get(i);

        TextView lblCompany = (TextView) v.findViewById(R.id.lblCompany);
        TextView lblValue = (TextView) v.findViewById(R.id.lblValue);

        lblCompany.setText(orderBook.getCompany());
        lblValue.setText("R$ "+Double.toString(orderBook.getValue()));
        return v;
    }
}
