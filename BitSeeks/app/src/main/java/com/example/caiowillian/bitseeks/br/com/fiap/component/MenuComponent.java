package com.example.caiowillian.bitseeks.br.com.fiap.component;

import android.content.Context;
import android.content.Intent;

import com.example.caiowillian.bitseeks.MainActivity;
import com.example.caiowillian.bitseeks.NewsActivity;
import com.example.caiowillian.bitseeks.OrderBookActivity;
import com.example.caiowillian.bitseeks.R;
import com.example.caiowillian.bitseeks.br.com.fiap.models.OrderBook;

/**
 * Created by Caio Willian on 13/10/2017.
 */

public class MenuComponent {

    public static Intent actionMenuItem(Context ctx, int id){
        if (id == R.id.nav_camera) {
            return new Intent(ctx,MainActivity.class);
        } else if (id == R.id.nav_gallery) {
            return new Intent(ctx,NewsActivity.class);
        } else if (id == R.id.nav_slideshow) {
            new CreateWalletDialog(ctx).createDialog();
        } else if (id == R.id.nav_manage) {
            return new Intent(ctx, OrderBookActivity.class);
        }

        return null;
    }

}
