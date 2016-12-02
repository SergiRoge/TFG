package tfg.lostandfound;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;

import Classes.User;
import Connection.SQLObject;
import Services.NotificationDaemon;

import static Auxiliar.Constants.*;

public class MyService extends Service {


    User user;
    String email;
    NotificationDaemon daemon;


    String pepito = "hola";

    MyTask myTask;
    private boolean exit = false;


    public MyService(User pUser)
    {
        user = pUser;



    }

    public MyService()
    {


    }

    @Override
    public IBinder onBind(Intent intent)
    {
        user = (User) intent.getSerializableExtra("User");


        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();


        myTask = new MyTask(user);

    }


    @Override
    public void onDestroy()
    {
        super.onDestroy();



    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        user = (User) intent.getSerializableExtra("User");



        myTask.tUser = user;

        myTask.execute();



        return super.onStartCommand(intent, flags, startId);
        //return 1;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User puser) {
        this.user = puser;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail()
    {
        return this.email;
    }

    private class MyTask extends AsyncTask<String, String, String> {

        private boolean cent;
        private User tUser;


        public MyTask(User pUser)
        {



            tUser = pUser;
        }
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();


            cent = true;
        }

        @Override
        protected String doInBackground(String... params) {
//            Toast.makeText(getApplicationContext(), "doInBackground", Toast.LENGTH_SHORT).show();

            while (cent)
            {
                try
                {
                    Thread.sleep(5000);
                    checkForMatchingItems();
                    publishProgress();
                    // Stop 5s

                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            return null;
        }


        private void checkForMatchingItems()
        {

            SQLObject sql = new SQLObject();

            String content = "email="+ tUser.getStrEmail();


            try
            {
                String strReturn = sql.ExecuteQuery(URL_CHECK_FOUND_ITEMS, "");

                Log.d("JSON DEVUELTO : ", "--> " + strReturn );
                JSONObject jsonObject = new JSONObject(strReturn);


                String IDItemLost = jsonObject.getString("IDItemLost");
                String IDItemFound = jsonObject.getString("IDItemFound");
                String strType = jsonObject.getString("Type");
                String strColor = jsonObject.getString("Color");
                String strMaterial = jsonObject.getString("Material");
                String strDescription = jsonObject.getString("Description");
                String RowID = jsonObject.getString("RowID");


                //Si esto se cumple, lanzamos notificacion
                if((Integer.parseInt(IDItemLost) != 0) && (Integer.parseInt(IDItemFound) != 0))
                {

                    Intent intent = new Intent("DATA");
                    intent.putExtra("IDItemLost", IDItemLost);
                    intent.putExtra("IDItemFound", IDItemFound);
                    intent.putExtra("Type", strType);
                    intent.putExtra("Color", strColor);
                    intent.putExtra("Material", strMaterial);
                    intent.putExtra("Description", strDescription);
                    intent.putExtra("RowID", RowID);

                    sendBroadcast(intent);

                }

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

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


    }



}