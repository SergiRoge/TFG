package Services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.view.Menu;

import tfg.lostandfound.MainActivity;

/**
 * Created by Llango on 22/11/2016.
 */

public class MatchingReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {



        // LANZAR SERVICIO
        Intent serviceIntent = new Intent();
        serviceIntent.setAction("tfg.lostandfound.MyService");
        context.startService(serviceIntent);



        // LANZAR ACTIVIDAD
        Intent i = new Intent(context, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
/*
    private void launchNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle("Notificación de Prueba");
        mBuilder.setContentText("Contenido");
        mBuilder.setAutoCancel(true);
        mBuilder.setTicker("Notificación de Prueba");
        mBuilder.setVisibility(NotificationCompat.VISIBILITY_SECRET);
        Intent resultIntent = new Intent(this, ResultActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(ResultActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationCompat.Action actionA =
                new NotificationCompat.Action(0, "Botón 1", resultPendingIntent);
        mBuilder.addAction(actionA);
        NotificationManager mNotificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(HELLO_ID, mBuilder.build());
    }    */

}
