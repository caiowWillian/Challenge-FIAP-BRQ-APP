package com.example.caiowillian.bitseeks.br.com.fiap.component;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caiowillian.bitseeks.R;

/**
 * Created by Caio Willian on 15/10/2017.
 */

public class CreateWalletDialog {
    private Context ctx;

    public CreateWalletDialog(Context ctx){ this.ctx = ctx; }

    public Dialog createDialog(){
        final Dialog dialog = new Dialog(ctx);
        dialog.setTitle("Create Wallet");
        dialog.setContentView(R.layout.dialog_create_wallet);

        final EditText edtPw = (EditText) dialog.findViewById(R.id.edtPw);
        final EditText edtConfPw = (EditText) dialog.findViewById(R.id.edtConf_pw);
        final EditText edtEmail = (EditText) dialog.findViewById(R.id.edtEmail);
        final TextView lblMsg = (TextView) dialog.findViewById(R.id.lblMessage);

        dialog.findViewById(R.id.btnCreate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(edtConfPw.getText() != null && edtPw.getText() != null &&  edtEmail.getText() != null){

                    if(!edtConfPw.getText().toString().isEmpty() || !edtPw.getText().toString().isEmpty() || !edtEmail.getText().toString().isEmpty()){
                        if( (edtPw.getText().toString().equals(edtConfPw.getText().toString()))){
                            final AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

                            builder.setTitle("Your Wallet ID");
                            builder.setMessage(java.util.UUID.randomUUID().toString());
                            builder.setCancelable(true);
                            builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            builder.show();

                            dialog.dismiss();

                        }else
                            Toast.makeText(ctx,"Senhas não conferem",Toast.LENGTH_LONG).show();
                    }else{
                        String field;
                        if(edtEmail.getText().toString().isEmpty()){
                            field = "Email";
                            edtEmail.findFocus();
                        }else
                            if(edtPw.getText().toString().isEmpty()){
                                field = "Senha";
                                edtPw.findFocus();
                            }else{
                                field = "Confirmação de senha";
                                edtConfPw.findFocus();
                            }
                        Toast.makeText(ctx,"Preencha o campo "+field,Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
        return dialog;
    }

    public static void hideSoftKeyboard (Context activity, View view)
    {
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }
}
