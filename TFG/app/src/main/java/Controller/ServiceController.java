package Controller;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

import Classes.Item;
import Classes.ItemViewList;
import Classes.User;
import Connection.Connection;
import Connection.ConnectionThread;
import tfg.lostandfound.MainActivity;
import tfg.lostandfound.R;

import static Auxiliar.Auxiliar.*;
import static Auxiliar.Auxiliar.createRandomItemViewListItem;
import static Auxiliar.Constants.*;

/**
 * Created by Llango on 18/10/2016.
 */

public class ServiceController {

    private User user;
    Intent intent;


    public ServiceController(User pUser)
    {
        this.user = pUser;
    }


    public void startService(Context context)
    {
        // Log.d("Username ", "-> " + user.getStrEmail() );
//        Log.d("Username 2", "-> " + service.getUser().getStrEmail() );
        startService(context);    }

    private void launchNotification() {
       /* NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle("Notificación de Prueba");
        mBuilder.setContentText("Contenido");
        mBuilder.setAutoCancel(true);
        mBuilder.setTicker("Notificación de Prueba");
        mBuilder.setVisibility(NotificationCompat.VISIBILITY_SECRET);

/*
        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);


        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setContentIntent(resultPendingIntent);
       NotificationCompat.Action actionA =
                new NotificationCompat.Action(0, "Botón 1", resultPendingIntent);
        mBuilder.addAction(actionA);

*//*
        //Si comento lo de arriba, no viajo a la pantalla.

        NotificationManager mNotificationManager = (NotificationManager)
                getSystemService(MainActivity.this.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());

        unregisterReceiver(myReceiver);
        //stopService(intentService);*/

    }




    private class ReceptorServicio extends BroadcastReceiver {
        @Override
        public void onReceive(Context arg0, Intent arg1) {
            /*if(!notificationsRead)
            {
                launchNotification();

            }*/

        }
    }

}
