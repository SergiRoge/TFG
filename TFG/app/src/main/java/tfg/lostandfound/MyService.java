package tfg.lostandfound;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import java.sql.Date;

import Services.NotificationDaemon;

public class MyService extends Service {


    private String email;
    NotificationDaemon daemon;

    MyTask myTask;


    public void setEmail(String email)
    {
        this.email = email;
    }



    public MyService()
    {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @Override
    public void onCreate()
    {
        super.onCreate();

        //Toast.makeText(this, "Servicio creado", Toast.LENGTH_LONG).show();
        Log.d("Servicio ", "Creado");
        daemon = new NotificationDaemon();
        myTask = new MyTask();
    }



    @Override
    public void onDestroy() {
        super.onDestroy();



        //Toast.makeText(this, "Servicio destruido ", Toast.LENGTH_LONG).show();
        Log.d("Servicio ", "destruido");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("onStartCommand ", "onStartCommand");

        myTask.execute();
        return super.onStartCommand(intent, flags, startId);
        //return 1;
    }




    private class MyTask extends AsyncTask<String, String, String> {

        private DateFormat dateFormat;
        private String date;
        private boolean cent;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("onPreExecute ", "onPreExecute");
            cent = true;
        }

        @Override
        protected String doInBackground(String... params)
        {
//            Toast.makeText(getApplicationContext(), "doInBackground", Toast.LENGTH_SHORT).show();

            while (cent){
                try {
                    Log.d("doInBackground ", "doInBackground");
                    checkForMatchingItems();
                    //publishProgress();
                    // Stop 5s
                    Thread.sleep(3600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            Toast.makeText(getApplicationContext(), "Hola k haces", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            cent = false;
        }

        public void checkForMatchingItems()
        {

        }



    }
}
