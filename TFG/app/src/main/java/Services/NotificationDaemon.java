package Services;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.Serializable;

public class NotificationDaemon extends Thread{


    @Override
    public void run()
    {
        while(true)
        {
            Log.d("Thread corriendo", "--> ");

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }
}
