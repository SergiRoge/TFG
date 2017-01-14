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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Classes.Item;
import Classes.ItemViewList;
import Connection.Connection;

import static Auxiliar.Constants.*;
import static Auxiliar.ErrorCode.*;

/**
 * Created by Llango on 16/10/2016.
 *
 * Class that contains generic methods called through entire application
 *
 */
public final class Auxiliar {

    private static ErrorCode errorCode = new ErrorCode();


    /**
     *  Method that converts an InputStream into a String.
     *
     * @param ists  The InputStream parameter.
     * @return      The converted string.
     * @throws IOException
     */
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


    /**
     *  Method that verifies if a field has forbidden or special chars.
     *
     * @param pstrValue The value of the field.
     * @return  false, if the value is not correct.
     *          true, if the value is correct.
     */
    public static boolean verifyNormalField(String pstrValue)
    {
        if(pstrValue.trim().length() < 4)
        {
            return false;
        }
        Pattern p = Pattern.compile("^[a-zA-Z0-9_-]{3,15}$");
        Matcher m = p.matcher(pstrValue);
        return m.matches();
    }

    /**
     *  Method that verifies if the email field has forbidden or special chars.
     *
     * @param pstrValue The value of the field.
     * @return  false, if the value is not correct.
     *          true, if the value is correct.
     */
    public static boolean verifyEmailField(String pstrValue)
    {
        if(pstrValue.trim().length() < 4)
        {
            return false;
        }
        Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher m = p.matcher(pstrValue);
        return m.matches();
    }

    /**
     * Method that handles the errors of the application by showing an alert with
     * the correct message error.
     *
     * @param context   Context of the application.
     * @param pintError the number of the error.
     */
    public static void showMessageError(Context context, int pintError)
    {

        String strTitle = "";
        String strMessage = "";
        String strButtonMessage = "Aceptar";
        switch (pintError)
        {

            case SERVER_ERROR:
                strTitle = ERROR;
                strMessage = "Ocurrió un error en el servidor";
                break;
            case INCORRECT_USER:
                strTitle = WARNING;
                strMessage = "Los datos introducidos son erróneos";
                break;
            case EMAIL_ALREADY_REGISTERED:
                strTitle = WARNING;
                strMessage = "El correo está ya registrado";
                break;
            case REGISTRATION_SUCCESFULLY:
                strTitle = WARNING;
                strMessage = "Usuario registrado correctamente";
                break;
            case INCORRECT_DATA:
                strTitle = WARNING;
                strMessage = "Campos incorrectos";
                break;
            case ITEM_REGISTERED_ALERT:
                strTitle = WARNING;
                strMessage = "Recuerda que siempre que puedes llevar el objeto que has encontrado al " +
                                "ayuntamiento del municipio donde lo encontraste.";
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
                        handler.sendMessage(handler.obtainMessage());
                        dialog.dismiss();
                    }
                });
        alertDialog.create();
        alertDialog.show();

        try { Looper.loop(); }
        catch(RuntimeException e2) {}

    }

    /**
     * Setter of the atribute perrorCode
      * @param perrorCode
     */
    public static void setErrorCode(int perrorCode) {
        errorCode.setErrorCode(perrorCode);
    }

}
