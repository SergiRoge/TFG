package tfg.lostandfound;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;

/**
 * Created by Llango on 23/11/2016.
 */

public class MyNotification extends AppCompatActivity {

    public  MyNotification()
    {

    }

    private void launchNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle("Notificación de Prueba");
        mBuilder.setContentText("Contenido");
        mBuilder.setAutoCancel(true);
        mBuilder.setTicker("Notificación de Prueba");
        mBuilder.setVisibility(NotificationCompat.VISIBILITY_SECRET);


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
        NotificationManager mNotificationManager = (NotificationManager)
                getSystemService(MyNotification.this.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());

    }
}
