package Services;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import tfg.lostandfound.MainActivity;
import tfg.lostandfound.MyService;

/**
 * Created by Llango on 23/11/2016.
 */

public class LaunchService extends Thread
{



    Context context;

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


    public void setContext(Context context) {
        this.context = context;
    }

}
