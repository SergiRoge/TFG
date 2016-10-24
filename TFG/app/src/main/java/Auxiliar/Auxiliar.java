package Auxiliar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Icon;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import Classes.Item;
import Classes.ItemViewList;
import Connection.Connection;

import static Auxiliar.Constants.*;
import static Auxiliar.ErrorCode.*;

/**
 * Created by Llango on 16/10/2016.
 */

public final class Auxiliar {

    private static ErrorCode errorCode = new ErrorCode();



    private Auxiliar()
    {

    }

    public static Item createRandomItem(int intItem)
    {
        //String pstrItemType, String pstrItemColor, String pstrItemBrand, String pstrItemMaterial, int pintWhen, String pstrStatus
        Item item = new Item("Scarf_"+intItem,"Black_"+intItem,"Nike_"+intItem,"Cloth_"+intItem,1,"Lost");
        return item;

    }
    public static String  createRandomItemViewListItem(int intItem)
    {
        //String pstrItemType, String pstrItemColor, String pstrItemBrand, String pstrItemMaterial, int pintWhen, String pstrStatus
        //R.drawable.icon_found
        return ("Scarf_"+intItem);
        // +", Black "+ intItem +", Nike "+intItem +", Cloth "+intItem + " Lost"
    }

    public static String convertinputStreamToString(InputStream ists)
            throws IOException {
        if (ists != null) {
            StringBuilder sb = new StringBuilder();
            String line;

            try {
                BufferedReader r1 = new BufferedReader(new InputStreamReader(
                        ists, "UTF-8"));
                while ((line = r1.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } finally {
                ists.close();
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    public static void showMessageError(Context context, int pintError)
    {

        String strTitle = "";
        String strMessage = "";
        String strButtonMessage = "Accept";
        switch (pintError)
        {

            case SERVER_ERROR:
                strTitle = ERROR;
                strMessage = "A server error happened";
                break;
            case INCORRECT_USER_PASSWORD:
                strTitle = WARNING;
                strMessage = "The user/ password is incorrect";
                break;
            case EMAIL_ALREADY_REGISTERED:
                strTitle = WARNING;
                strMessage = "The email is already registered";
                break;
            case REGISTRATION_SUCCESFULLY:
                strTitle = WARNING;
                strMessage = "User registered succesfully";
                break;


            default:
                strTitle = WARNING;
                strMessage = "Unkown error";
                break;
        }

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message mesg) {
                throw new RuntimeException();
            }
        };


        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(strTitle);
        alertDialog.setMessage(strMessage);
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
        alertDialog.setPositiveButton(
                strButtonMessage,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int a = doSomethingWithValue("hola");
                        handler.sendMessage(handler.obtainMessage());
                        dialog.dismiss();
                    }
                });
        alertDialog.create();
        alertDialog.show();

        try { Looper.loop(); }
        catch(RuntimeException e2) {}

    }
    public static int doSomethingWithValue(String hoa)
    {
        return 1;
    }

    public static ErrorCode getErrorCode() {
        return errorCode;
    }

    public static void setErrorCode(int perrorCode) {
        errorCode.setErrorCode(perrorCode);
    }

}
