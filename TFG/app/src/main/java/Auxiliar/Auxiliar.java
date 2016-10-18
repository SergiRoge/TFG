package Auxiliar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Icon;
import android.util.Log;


import Connection.Connection;

import static Auxiliar.Constants.*;

/**
 * Created by Llango on 16/10/2016.
 */

public final class Auxiliar {




    private Auxiliar()
    {

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
            default:
                break;
        }

        AlertDialog.Builder alertDialog  = new AlertDialog.Builder(context);
        alertDialog.setTitle(strTitle);
        alertDialog.setMessage(strMessage);
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
        Log.d("Context ", " -> " + context);
        alertDialog.setPositiveButton(
                strButtonMessage,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();

    }


}
